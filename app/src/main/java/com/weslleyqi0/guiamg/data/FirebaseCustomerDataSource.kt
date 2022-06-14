package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.weslleyqi0.guiamg.domain.model.Customer
import com.weslleyqi0.guiamg.util.COLLECTION_CUSTOMER
import com.weslleyqi0.guiamg.util.COLLECTION_DATA
import com.weslleyqi0.guiamg.util.STORAGE_IMAGES
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseCustomerDataSource @Inject constructor(
    firebaseDatabase: FirebaseDatabase,
    firebaseStorage: FirebaseStorage
) : CustomerDataSource {

    private val customerReference  = firebaseDatabase.reference
        .child(COLLECTION_DATA).child(COLLECTION_CUSTOMER)

    private val storageReference = firebaseStorage.reference

    override suspend fun getCustomers(): List<Customer> {
        return suspendCoroutine { continuation ->
            customerReference.get().addOnSuccessListener { dataSnapshot ->
                val customers = mutableListOf<Customer>()
                for (snapshot in dataSnapshot.children) {
                    val customer = snapshot.getValue(Customer::class.java)
                    customer?.let {
                        customers.add(it)
                    }
                }
                continuation.resumeWith(Result.success(customers))
            }
            customerReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }

    override suspend fun uploadCustomerImage(customerUUID: String, imageUri: Uri): String {
        return suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID()
            val childReference = storageReference.child(STORAGE_IMAGES).child(COLLECTION_CUSTOMER)
                .child(customerUUID).child(customerUUID)

            childReference.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val path = uri.toString()
                        continuation.resumeWith(Result.success(path))
                    }.addOnFailureListener { exception ->
                        continuation.resumeWith(Result.failure(exception))
                    }
                }

        }
    }

    override suspend fun createCustomer(customer: Customer): Customer {
        return suspendCoroutine { continuation ->
                customerReference.child(customer.id).setValue(customer)
                    .addOnSuccessListener {
                        continuation.resumeWith(Result.success(customer))
                    }.addOnFailureListener { exception ->
                        continuation.resumeWith(Result.failure(exception))
                    }
            }
        }
}
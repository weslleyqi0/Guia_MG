package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.weslleyqi0.guiamg.domain.model.Client
import com.weslleyqi0.guiamg.util.COLLECTION_CLIENTS
import com.weslleyqi0.guiamg.util.COLLECTION_DATA
import com.weslleyqi0.guiamg.util.STORAGE_IMAGES
import java.util.*
import kotlin.coroutines.suspendCoroutine

class FirebaseClientDataSource(
    firebaseDatabase: FirebaseDatabase,
    firebaseStorage: FirebaseStorage
) : ClientDataSource {

    private val clientReference  = firebaseDatabase.reference
        .child(COLLECTION_DATA).child(COLLECTION_CLIENTS)

    private val storageReference = firebaseStorage.reference

    override suspend fun getClients(): List<Client> {
        return suspendCoroutine { continuation ->
            clientReference.get().addOnSuccessListener { dataSnapshot ->
                val clients = mutableListOf<Client>()
                for (snapshot in dataSnapshot.children) {
                    val client = snapshot.getValue(Client::class.java)
                    client?.let {
                        clients.add(it)
                    }
                }
                continuation.resumeWith(Result.success(clients))
            }
            clientReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }

    override suspend fun uploadClientImage( clientUUID: String, imageUri: Uri): String {
        return suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID()
            val childReference = storageReference.child(STORAGE_IMAGES).child(COLLECTION_CLIENTS)
                .child(clientUUID).child(randomKey.toString())

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

    override suspend fun createClient(client: Client): Client {
        return suspendCoroutine { continuation ->
                clientReference.child(client.id).setValue(client)
                    .addOnSuccessListener {
                        continuation.resumeWith(Result.success(client))
                    }.addOnFailureListener { exception ->
                        continuation.resumeWith(Result.failure(exception))
                    }
            }
        }
}
package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.data.CustomerRepository
import com.weslleyqi0.guiamg.domain.model.Customer
import java.util.*
import javax.inject.Inject

class CreateCustomerUseCaseImpl @Inject constructor(
    private val uploadCustomerImageUseCase: UploadCustomerImageUseCase,
    private val customerRepository: CustomerRepository
) : CreateCustomerUseCase {
    override suspend fun invoke(customer: Customer, imageUri: Uri): Customer {
        return try {
            val customerUUID = UUID.randomUUID().toString()
            val imageUrl = uploadCustomerImageUseCase(customer.id, imageUri)
            customer.mainImage = imageUrl
            customerRepository.createCustomer(customer)
        }catch(e : Exception){
            throw  e
        }
    }

}
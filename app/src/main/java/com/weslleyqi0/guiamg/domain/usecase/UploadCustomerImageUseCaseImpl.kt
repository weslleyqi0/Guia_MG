package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.data.CustomerRepository
import javax.inject.Inject

class UploadCustomerImageUseCaseImpl @Inject constructor(
    private val customerRepository: CustomerRepository
) : UploadCustomerImageUseCase{
    override suspend fun invoke(customerUUID: String, imageUri: Uri): String {
        return customerRepository.uploadCustomerImage(customerUUID, imageUri)
    }
}
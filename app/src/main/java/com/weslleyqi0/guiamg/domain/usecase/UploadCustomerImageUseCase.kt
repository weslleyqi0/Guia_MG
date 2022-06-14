package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri

interface UploadCustomerImageUseCase {

    suspend operator fun invoke(customerUUID: String, imageUri: Uri) : String
}
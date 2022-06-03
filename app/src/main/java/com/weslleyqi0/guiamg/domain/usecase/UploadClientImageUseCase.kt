package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri

interface UploadClientImageUseCase {

    suspend operator fun invoke(clientUUID: String, imageUri: Uri) : String
}
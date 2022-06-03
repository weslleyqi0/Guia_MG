package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.data.ClientRepository

class UploadClientImageUseCaseImpl(
    private val clientRepository: ClientRepository
) : UploadClientImageUseCase{
    override suspend fun invoke(clientUUID: String, imageUri: Uri): String {
        return clientRepository.uploadClientImage(clientUUID, imageUri)
    }
}
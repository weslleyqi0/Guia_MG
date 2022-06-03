package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.data.ClientRepository
import com.weslleyqi0.guiamg.domain.model.Client
import java.util.*

class CreateClientUseCaseImpl(
    private val uploadClientImageUseCase: UploadClientImageUseCase,
    private val clientRepository: ClientRepository
) : CreateClientUseCase {
    override suspend fun invoke(client: Client, imageUri: Uri): Client {
        return try {
            val clientUUID = UUID.randomUUID().toString()
            val imageUrl = uploadClientImageUseCase(clientUUID, imageUri)
            client.mainImage = imageUrl
            clientRepository.createClient(client)
        }catch(e : Exception){
            throw  e
        }
    }

}
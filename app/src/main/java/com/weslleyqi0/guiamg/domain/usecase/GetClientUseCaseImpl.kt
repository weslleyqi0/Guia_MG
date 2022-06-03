package com.weslleyqi0.guiamg.domain.usecase

import com.weslleyqi0.guiamg.data.ClientRepository
import com.weslleyqi0.guiamg.domain.model.Client

class GetClientUseCaseImpl(
    private val clientRepository: ClientRepository
) : GetClientUseCase {
    override suspend fun invoke(): List<Client> {
        return clientRepository.getClients()
    }
}

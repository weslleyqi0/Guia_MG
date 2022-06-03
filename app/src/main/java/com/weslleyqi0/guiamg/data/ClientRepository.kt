package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Client

class ClientRepository(private val dataSource: ClientDataSource) {

    suspend fun getClients() : List<Client> = dataSource.getClients()

    suspend fun uploadClientImage(clientUUID: String, imageUri: Uri) : String = dataSource.uploadClientImage("", imageUri)

    suspend fun  createClient(client: Client) : Client = dataSource.createClient(client)

}
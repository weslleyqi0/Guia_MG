package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Client

interface ClientDataSource {

    suspend fun getClients() : List<Client>

    suspend fun uploadClientImage(clientUUID: String, imageUri: Uri) : String

    suspend fun  createClient(client: Client) : Client
}
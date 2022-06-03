package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Client

interface CreateClientUseCase {

    suspend operator fun invoke(client: Client, imageUri: Uri) : Client
}
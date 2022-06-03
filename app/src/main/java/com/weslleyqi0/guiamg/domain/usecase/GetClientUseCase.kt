package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Client

interface GetClientUseCase {
    suspend operator fun invoke() : List<Client>
}
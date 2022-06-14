package com.weslleyqi0.guiamg.domain.usecase

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Customer

interface CreateCustomerUseCase {

    suspend operator fun invoke(customer: Customer, imageUri: Uri) : Customer
}
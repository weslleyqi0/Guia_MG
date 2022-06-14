package com.weslleyqi0.guiamg.domain.usecase

import com.weslleyqi0.guiamg.domain.model.Customer

interface GetCustomerUseCase {
    suspend operator fun invoke() : List<Customer>
}
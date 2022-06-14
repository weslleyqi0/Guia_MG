package com.weslleyqi0.guiamg.domain.usecase

import com.weslleyqi0.guiamg.domain.model.Customer

interface GetCustomersUseCase {
    suspend operator fun invoke() : List<Customer>
}
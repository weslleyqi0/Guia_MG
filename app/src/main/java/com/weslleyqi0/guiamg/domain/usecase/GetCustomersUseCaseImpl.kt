package com.weslleyqi0.guiamg.domain.usecase

import com.weslleyqi0.guiamg.data.CustomerRepository
import com.weslleyqi0.guiamg.domain.model.Customer
import javax.inject.Inject

class GetCustomersUseCaseImpl @Inject constructor(
    private val customerRepository: CustomerRepository
) : GetCustomersUseCase {
    override suspend fun invoke(): List<Customer> {
        return customerRepository.getCustomers()
    }
}

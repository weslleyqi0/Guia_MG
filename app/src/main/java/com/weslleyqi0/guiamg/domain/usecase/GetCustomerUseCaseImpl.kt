package com.weslleyqi0.guiamg.domain.usecase

import com.weslleyqi0.guiamg.data.CustomerRepository
import com.weslleyqi0.guiamg.domain.model.Customer
import javax.inject.Inject

class GetCustomerUseCaseImpl @Inject constructor(
    private val customerRepository: CustomerRepository
) : GetCustomerUseCase {
    override suspend fun invoke(): List<Customer> {
        return customerRepository.getCustomer()
    }
}

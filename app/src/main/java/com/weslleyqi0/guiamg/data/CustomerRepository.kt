package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Customer
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val dataSource: CustomerDataSource)
{

    suspend fun getCustomers() : List<Customer> = dataSource.getCustomers()

    suspend fun uploadCustomerImage(customerUUID: String, imageUri: Uri) : String = dataSource.uploadCustomerImage(customerUUID, imageUri)

    suspend fun  createCustomer(customer: Customer) : Customer = dataSource.createCustomer(customer)

}
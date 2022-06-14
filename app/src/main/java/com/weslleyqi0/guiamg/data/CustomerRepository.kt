package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Customer

class CustomerRepository(private val dataSource: CustomerDataSource) {

    suspend fun getCustomer() : List<Customer> = dataSource.getCustomer()

    suspend fun uploadCustomerImage(customerUUID: String, imageUri: Uri) : String = dataSource.uploadCustomerImage(customerUUID, imageUri)

    suspend fun  createCustomer(customer: Customer) : Customer = dataSource.createCustomer(customer)

}
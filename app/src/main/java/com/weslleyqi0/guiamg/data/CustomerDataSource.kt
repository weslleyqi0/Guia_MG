package com.weslleyqi0.guiamg.data

import android.net.Uri
import com.weslleyqi0.guiamg.domain.model.Customer

interface CustomerDataSource {

    suspend fun getCustomers() : List<Customer>

    suspend fun uploadCustomerImage(customerUUID: String, imageUri: Uri) : String

    suspend fun  createCustomer(customer: Customer) : Customer
}
package com.weslleyqi0.guiamg.ui.addcustomer

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weslleyqi0.guiamg.R
import com.weslleyqi0.guiamg.domain.model.Customer
import com.weslleyqi0.guiamg.domain.usecase.CreateCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(
    private val createCustomerUseCase: CreateCustomerUseCase
) : ViewModel() {

    private var isFormValid = false

    private val _imageUriErrorResId = MutableLiveData<Int>()
    val imageUriErrorResId: LiveData<Int> = _imageUriErrorResId

    private val _nameFieldErrorResId = MutableLiveData<Int?>()
    val nameFieldErrorResId: LiveData<Int?> = _nameFieldErrorResId

    private val _descriptionFieldErrorResId = MutableLiveData<Int?>()
    val descriptionFieldErrorResId: LiveData<Int?> = _descriptionFieldErrorResId

    private val _addressFieldErrorResId = MutableLiveData<Int?>()
    val addressFieldErrorResId: LiveData<Int?> = _addressFieldErrorResId

    private val _phoneFieldErrorResId = MutableLiveData<Int?>()
    val phoneFieldErrorResId: LiveData<Int?> = _phoneFieldErrorResId

    private val _categoryFieldErrorResId = MutableLiveData<Int?>()
    val categoryFieldErrorResId: LiveData<Int?> = _categoryFieldErrorResId

    private val _customerCreated = MutableLiveData<Customer>()
    val customerCreated: LiveData<Customer> = _customerCreated

    fun createCustomer(
        imageUri: Uri?,
        name: String,
        address: String,
        phone: String,
        instagramLink: String,
        facebookLink: String,
        mapsLink: String,
        description: String,
        category: String,
    ) = viewModelScope.launch{

        isFormValid = true

        _imageUriErrorResId.value = getDrawableResIdIfNull(imageUri)
        _nameFieldErrorResId.value = getErrorStringResIdIfEmpty(name)
        _descriptionFieldErrorResId.value = getErrorStringResIdIfEmpty(description)
        _addressFieldErrorResId.value = getErrorStringResIdIfEmpty(address)
        _phoneFieldErrorResId.value = getErrorStringResIdIfEmpty(phone)
        _categoryFieldErrorResId.value = getErrorStringResIdIfEmpty(category)

        if (isFormValid) {
            try {
                val keyUUID = UUID.randomUUID().toString()

                val customer = Customer(
                    id = keyUUID,
                    name = name,
                    description = description,
                    address = address,
                    categories = arrayListOf(category),
                    phone = phone,
                    instagramLink = instagramLink,
                    facebookLink = facebookLink,
                    mapsLink = mapsLink,
                    mainImage = "",
                    addedAt = System.currentTimeMillis().toString(),
                    highlighted = false,
                    imagesList = arrayListOf()
                )

                val customerResult = createCustomerUseCase(customer, imageUri!!)
                _customerCreated.value = customerResult

                Log.d("CreateCustomer--->", "OK")
            } catch (e: Exception) {
                Log.d("CreateCustomer--->", e.toString())
            }
        }
    }

    private fun getErrorStringResIdIfEmpty(value: String): Int? {
        return if (value.isEmpty()) {
            isFormValid = false
            R.string.add_customer_field_error
        } else null
    }

    private fun getDrawableResIdIfNull(value: Uri?): Int {
        return if (value == null) {
            isFormValid = false
            R.drawable.background_customer_image_error
        } else R.drawable.background_customer_image
    }
}
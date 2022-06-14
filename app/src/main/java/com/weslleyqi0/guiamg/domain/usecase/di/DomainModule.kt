package com.weslleyqi0.guiamg.domain.usecase.di

import com.weslleyqi0.guiamg.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindCreateCustomerUseCase(useCase: CreateCustomerUseCaseImpl) : CreateCustomerUseCase

    @Binds
    fun bindGetCustomersUseCase(useCaseImpl: GetCustomersUseCaseImpl) : GetCustomersUseCase

    @Binds
    fun bindUploadCustomerImageUseCase(useCase: UploadCustomerImageUseCaseImpl) : UploadCustomerImageUseCase
}
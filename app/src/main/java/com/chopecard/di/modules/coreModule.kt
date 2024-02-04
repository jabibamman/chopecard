package com.chopecard.di.modules

import CollectorViewModel
import ManageFavoritesUseCase
import com.chopecard.data.repository.CardRepository
import com.chopecard.data.repository.StoreRepository
import com.chopecard.data.repository.UserRepository
import com.chopecard.data.repository.impl.CardRepositoryImpl
import com.chopecard.data.repository.impl.StoreRepositoryImpl
import com.chopecard.data.repository.impl.UserRepositoryImpl
import com.chopecard.domain.usecases.AddProductUseCase
import com.chopecard.domain.usecases.CreateStoreUseCase
import com.chopecard.domain.usecases.CreateUserUseCase
import com.chopecard.domain.usecases.DeleteProductUseCase
import com.chopecard.domain.usecases.GetReservationsUseCase
import com.chopecard.domain.usecases.GetStoreProductsUseCase
import com.chopecard.domain.usecases.GetStoresUseCase
import com.chopecard.domain.usecases.GetUserUseCase
import com.chopecard.domain.usecases.ReserveProductUseCase
import com.chopecard.domain.usecases.UnreserveProductUseCase
import com.chopecard.domain.usecases.UpdateProductUseCase
import com.chopecard.presentation.viewModel.CardViewModel
import com.chopecard.presentation.viewModel.LoginViewModel
import com.chopecard.presentation.viewModel.SellerViewModel
import com.chopecard.presentation.viewModel.StoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module where all the core classes to inject must be declared
 */
internal val coreModule = module {
    // Repositories
    single<CardRepository> { CardRepositoryImpl(get()) }
    single<StoreRepository> { StoreRepositoryImpl(get()) }
    single { UserRepositoryImpl(get()) as UserRepository }

    // Use Cases
    factory { AddProductUseCase(get()) }
    factory { CreateStoreUseCase(get()) }
    factory { DeleteProductUseCase(get()) }
    factory { GetReservationsUseCase(get()) }
    factory { GetStoreProductsUseCase(get()) }
    factory { GetStoresUseCase(get()) }
    factory { ManageFavoritesUseCase(get()) }
    factory { ReserveProductUseCase(get()) }
    factory { UnreserveProductUseCase(get()) }
    factory { UpdateProductUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }

    // ViewModels
    viewModel { CollectorViewModel(get()) }
    viewModel { SellerViewModel(get(), get(), get(), get(), get()) }
    viewModel { CardViewModel(get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }

    // ... autres déclarations de ViewModel

}

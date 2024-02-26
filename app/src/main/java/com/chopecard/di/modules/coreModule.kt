package com.chopecard.di.modules

import com.chopecard.data.repository.AdminRepository
import com.chopecard.data.repository.CardRepository
import com.chopecard.data.repository.ProductRepository
import com.chopecard.data.repository.StoreRepository
import com.chopecard.data.repository.UserRepository
import com.chopecard.data.repository.impl.AdminRepositoryImpl
import com.chopecard.data.repository.impl.CardRepositoryImpl
import com.chopecard.data.repository.impl.ProductRepositoryImpl
import com.chopecard.data.repository.impl.ProductRepositoyImpl
import com.chopecard.data.repository.impl.StoreRepositoryImpl
import com.chopecard.data.repository.impl.UserRepositoryImpl
import com.chopecard.domain.usecases.AddProductUseCase
import com.chopecard.domain.usecases.AddTicketUseCase
import com.chopecard.domain.usecases.CreateStoreUseCase
import com.chopecard.domain.usecases.CreateUserUseCase
import com.chopecard.domain.usecases.DeleteProductUseCase
import com.chopecard.domain.usecases.DeleteTicketUseCase
import com.chopecard.domain.usecases.GetProductDetailUseCase
import com.chopecard.domain.usecases.GetProductsUseCase
import com.chopecard.domain.usecases.GetStoreProductsUseCase
import com.chopecard.domain.usecases.GetStoresUseCase
import com.chopecard.domain.usecases.GetTicketsUseCase
import com.chopecard.domain.usecases.GetUserReservationsUseCase
import com.chopecard.domain.usecases.GetUserUseCase
import com.chopecard.domain.usecases.ManageFavoritesUseCase
import com.chopecard.domain.usecases.ReserveProductUseCase
import com.chopecard.domain.usecases.UnreserveProductUseCase
import com.chopecard.domain.usecases.UpdateProductUseCase
import com.chopecard.domain.usecases.yugioh.GetYugiohCardInfoByNameUseCase
import com.chopecard.presentation.viewModel.CardViewModel
import com.chopecard.presentation.viewModel.CollectorViewModel
import com.chopecard.presentation.viewModel.LoginViewModel
import com.chopecard.presentation.viewModel.ProductDetailViewModel
import com.chopecard.presentation.viewModel.SellerViewModel
import com.chopecard.presentation.viewModel.StoreViewModel
import com.chopecard.presentation.viewModel.TicketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module where all the core classes to inject must be declared
 */
internal val coreModule = module {
    // Repositories
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CardRepository> { CardRepositoryImpl(get()) }
    single<StoreRepository> { StoreRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<AdminRepository> { AdminRepositoryImpl(get()) }
    single<ProductRepository> { ProductRepositoyImpl(get()) }

    // Use Cases
    factory { AddProductUseCase(get()) }
    factory { AddTicketUseCase(get()) }
    factory { CreateStoreUseCase(get()) }
    factory { DeleteProductUseCase(get()) }
    factory { DeleteTicketUseCase(get()) }
    factory { GetStoreProductsUseCase(get()) }
    factory { GetStoresUseCase(get()) }
    factory { GetTicketsUseCase(get()) }
    factory { ManageFavoritesUseCase(get()) }
    factory { ReserveProductUseCase(get()) }
    factory { UnreserveProductUseCase(get()) }
    factory { UpdateProductUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { GetYugiohCardInfoByNameUseCase(get()) }
    factory { GetStoreProductsUseCase(get()) }
    factory { GetProductsUseCase(get()) }
    factory { GetProductDetailUseCase(get()) }
    factory { GetUserReservationsUseCase(get()) }

    // ViewModels
    viewModel { CollectorViewModel(get()) }
    viewModel { SellerViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { CardViewModel(get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { TicketViewModel(get(), get(), get()) }
    viewModel { ProductDetailViewModel(get()) }
}

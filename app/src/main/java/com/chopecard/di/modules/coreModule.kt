package com.chopecard.di.modules

import ApiService
import CollectorViewModel
import FetchProductsUseCase
import ManageFavoritesUseCase
import SellerViewModel
import StoreRepositoryImpl
import UpdateStockUseCase
import com.chopecard.data.repository.CardRepository
import com.chopecard.data.repository.CardRepositoryImpl
import com.chopecard.data.repository.StoreRepository
import com.chopecard.presentation.viewModel.CardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module where all the core classes to inject must be declared
 */
internal val coreModule = module {
    // Repositories
    single<CardRepository> { CardRepositoryImpl(get()) }
    single<StoreRepository> { StoreRepositoryImpl(get()) }

    // Use Cases
    factory { FetchProductsUseCase(get()) }
    factory { ManageFavoritesUseCase(get()) }
    factory { UpdateStockUseCase(get()) }

    // ViewModels
    viewModel { CollectorViewModel(get()) }
    viewModel { SellerViewModel(get()) }
    viewModel { CardViewModel(get()) }
    // ... autres déclarations de ViewModel

    // Fournir les services API
    single { provideRetrofit() }
    single { get<Retrofit>().create(ApiService::class.java) }
}

// Fonction pour configurer Retrofit
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://exemple-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

package com.chopecard.di.modules

import ApiService
import CollectorViewModel
import ManageFavoritesUseCase
import com.chopecard.data.repository.CardRepository
import com.chopecard.data.repository.StoreRepository
import com.chopecard.data.repository.impl.CardRepositoryImpl
import com.chopecard.data.repository.impl.StoreRepositoryImpl
import com.chopecard.domain.usecases.AddProductUseCase
import com.chopecard.domain.usecases.CreateStoreUseCase
import com.chopecard.domain.usecases.DeleteProductUseCase
import com.chopecard.domain.usecases.GetReservationsUseCase
import com.chopecard.domain.usecases.GetStoreProductsUseCase
import com.chopecard.domain.usecases.GetStoresUseCase
import com.chopecard.domain.usecases.ReserveProductUseCase
import com.chopecard.domain.usecases.UnreserveProductUseCase
import com.chopecard.domain.usecases.UpdateProductUseCase
import com.chopecard.presentation.viewModel.CardViewModel
import com.chopecard.presentation.viewModel.SellerViewModel
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

    // ViewModels
    viewModel { CollectorViewModel(get()) }
    viewModel { SellerViewModel(get(), get(), get(), get()) }
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

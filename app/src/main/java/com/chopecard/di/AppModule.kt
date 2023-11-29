import com.chopecard.data.repository.ProductRepository
import com.chopecard.data.repository.StoreRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get

val appModule = module {
    // Repositories
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<StoreRepository> { StoreRepositoryImpl(get()) }

    // Use Cases
    factory { FetchProductsUseCase(get()) }
    factory { ManageFavoritesUseCase(get()) }
    factory { UpdateStockUseCase(get()) }

    // ViewModels
    viewModel { CollectorViewModel(get()) }
    viewModel { SellerViewModel(get()) }
    // ... autres déclarations de ViewModel

    // Fournir les services API (si vous utilisez Retrofit, par exemple)
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

package com.chopecard.presentation.viewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.models.Product
import com.chopecard.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.launch

class CollectorViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    val productLiveData = MutableLiveData<ProductDataState>()

    fun getProducts() {
        productLiveData.postValue(ProductDataState.Loading)
        viewModelScope.launch {
            try {
                val products = getProductsUseCase.execute()
                productLiveData.postValue(ProductDataState.Success(products))
                Log.d("CollectorViewModel", "Products retrieved: $products")
            } catch (e: Exception) {
                productLiveData.postValue(ProductDataState.Error(e))
                Log.e("CollectorViewModel", "Error retrieving products", e)
            }
        }
    }

}

sealed class ProductDataState {
    object Loading : ProductDataState()
    data class Success(val products: List<Product>) : ProductDataState()
    data class Error(val exception: Throwable) : ProductDataState()
}

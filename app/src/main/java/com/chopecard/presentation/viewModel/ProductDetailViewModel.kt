package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.Card
import com.chopecard.domain.usecases.GetTicketsUseCase
import com.chopecard.domain.usecases.GetUserUseCase
import com.chopecard.domain.usecases.yugioh.GetYugiohCardInfoByNameUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

/** Represents the UI state. */
sealed class ProductDetailState {
    object Loading : ProductDetailState()
    data class Success(val product: List<Card>): ProductDetailState()
    data class Error(val exception: String): ProductDetailState()
}

class ProductDetailViewModel(
    private val getYugiohCardInfoByNameUseCase: GetYugiohCardInfoByNameUseCase,
    ) : ViewModel() {
    val productLiveData = MutableLiveData<ProductDetailState>()

    fun loadProductDetail(productName: String) {
        productLiveData.postValue(ProductDetailState.Loading)
        viewModelScope.launch {
            try {
                val product = getYugiohCardInfoByNameUseCase.execute(productName)
                productLiveData.postValue(ProductDetailState.Success(product))
                Log.d("ProductDetailViewModel", "Product: $product")
            } catch (e: HttpException) {
                productLiveData.postValue(ProductDetailState.Error("HTTP Exception: ${e.message()}"))
                Log.e("ProductDetailViewModel", "HTTP Exception: ${e.message()}")
            } catch (e: Exception) {
                productLiveData.postValue(ProductDetailState.Error("Exception: ${e.message}"))
                Log.e("ProductDetailViewModel", "Exception: ${e.message}")
            }
        }
    }
}
package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.Card
import com.chopecard.domain.usecases.yugioh.GetYugiohCardInfoByNameUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed class ProductDetailState {
    object Loading : ProductDetailState()
    data class Success(val product: List<Card>) : ProductDetailState()
    data class Error(val message: String) : ProductDetailState()
}

class ProductDetailViewModel(
    private val getYugiohCardInfoByNameUseCase: GetYugiohCardInfoByNameUseCase
) : ViewModel() {
    val productLiveData = MutableLiveData<ProductDetailState>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    fun loadProductDetail(productName: String) {
        productLiveData.postValue(ProductDetailState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val product = getYugiohCardInfoByNameUseCase.execute(productName)
                if (product.isEmpty()) {
                    productLiveData.postValue(ProductDetailState.Error("Product not found"))
                } else {
                    productLiveData.postValue(ProductDetailState.Success(product))
                }
            } catch (e: HttpException) {
                handleException(e)
            }
        }
    }

    private fun handleException(exception: Throwable) {
        val errorMessage = when (exception) {
            is HttpException -> "HTTP Error: ${exception.message}"
            else -> "An error occurred: ${exception.localizedMessage}"
        }
        productLiveData.postValue(ProductDetailState.Error(errorMessage))
        Log.e("ProductDetailViewModel", errorMessage)
    }
}
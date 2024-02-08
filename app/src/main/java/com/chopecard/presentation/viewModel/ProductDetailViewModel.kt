package com.chopecard.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.models.Product
import com.chopecard.domain.usecases.GetProductDetailUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
class ProductDetailViewModel(private val getProductDetailUseCase: GetProductDetailUseCase) : ViewModel() {

    private val _productLiveData = MutableLiveData<Product>()
    val productLiveData: LiveData<Product> = _productLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun loadProductDetail(productId: Int) {
        viewModelScope.launch {
            try {
                val product = getProductDetailUseCase.execute(productId)
                _productLiveData.value = product
            } catch (e: HttpException) {
                _errorLiveData.value = "HTTP Exception: ${e.message()}"
            } catch (e: Exception) {
                _errorLiveData.value = "Exception: ${e.message}"
            }
        }
    }
}

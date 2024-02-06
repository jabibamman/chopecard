package com.chopecard.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.models.Product
import com.chopecard.domain.usecases.GetProductDetailUseCase
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val getProductDetailUseCase: GetProductDetailUseCase) : ViewModel() {

    private val _productDetail = MutableLiveData<Product>()
    val productDetail: LiveData<Product> = _productDetail

    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            val productDetail = getProductDetailUseCase.execute(productId)
            _productDetail.value = productDetail
        }
    }
}

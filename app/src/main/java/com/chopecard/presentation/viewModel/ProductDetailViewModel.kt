package com.chopecard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.usecases.GetProductDetailUseCase
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val getProductDetailUseCase: GetProductDetailUseCase) : ViewModel() {

    /*
    private val _productDetail = MutableLiveData<Product>()
    val productDetail: LiveData<Product> = _productDetail
*/


    fun loadProductDetail(productId: Int) {
        viewModelScope.launch {
            try {
                getProductDetailUseCase.execute(productId);
                // Update UI accordingly based on success

            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

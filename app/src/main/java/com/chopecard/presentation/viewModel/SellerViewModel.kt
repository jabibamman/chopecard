package com.chopecard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.domain.usecases.AddProductUseCase
import com.chopecard.domain.usecases.CreateStoreUseCase
import com.chopecard.domain.usecases.GetStoreUseCase
import com.chopecard.domain.usecases.GetStoresUseCase
import kotlinx.coroutines.launch

class SellerViewModel(
    private val getStoresUseCase: GetStoresUseCase,
    private val createStoreUseCase: CreateStoreUseCase,
    private val getStoreUseCase: GetStoreUseCase,
    private val addProductUseCase: AddProductUseCase

    // Add other use cases as needed
) : ViewModel() {

    fun addProduct(storeId: Int, productId: Int, productStoreDTO: ProductStoreDTO) {
        viewModelScope.launch {
            try {
                addProductUseCase.execute(CreateProductDTO(storeId, productId, productStoreDTO))
                // Update UI accordingly based on success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    // Implement other actions (e.g., updateProduct, deleteProduct) similarly
}

package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.domain.usecases.AddProductUseCase
import com.chopecard.domain.usecases.GetStoresUseCase
import kotlinx.coroutines.launch

class SellerViewModel(
    private val getStoresUseCase: GetStoresUseCase,
    private val addProductUseCase: AddProductUseCase

    // Add other use cases as needed
) : ViewModel() {
    val alertMessage = MutableLiveData<String>()


    fun addProduct(storeId: Int, productId: Int, productStoreDTO: ProductStoreDTO) {
        viewModelScope.launch {
            try {
                val result = addProductUseCase.execute(CreateProductDTO(storeId, productId, productStoreDTO))
                Log.d("SellerViewModel", "Successfully added product: $result")
                alertMessage.postValue("Produit ajouté avec succès au magasin.")

            } catch (e: Exception) {
                Log.e("SellerViewModel", "Error adding product", e)
                alertMessage.postValue("Erreur lors de l'ajout du produit.")

            }
        }
    }

    fun getStores() {
        viewModelScope.launch {
            try {
                getStoresUseCase.execute()
                // Update UI accordingly based on success

            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    // Implement other actions (e.g., updateProduct, deleteProduct) similarly
}

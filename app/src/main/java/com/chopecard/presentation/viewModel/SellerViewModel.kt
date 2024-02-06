package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.model.DeleteProductDTO
import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.UpdateProductDTO
import com.chopecard.domain.usecases.AddProductUseCase
import com.chopecard.domain.usecases.DeleteProductUseCase
import com.chopecard.domain.usecases.GetStoresUseCase
import com.chopecard.domain.usecases.ReserveProductUseCase
import com.chopecard.domain.usecases.UnreserveProductUseCase
import com.chopecard.domain.usecases.UpdateProductUseCase
import kotlinx.coroutines.launch

class SellerViewModel(
    private val getStoresUseCase: GetStoresUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val reserveProductUseCase: ReserveProductUseCase,
    private val unreserveProductUseCase: UnreserveProductUseCase
    // Add other use cases as needed
) : ViewModel() {
    val alertMessage = MutableLiveData<String>()


    fun addProduct(storeId: Int, productId: Int, productStoreDTO: ProductStoreDTO) {
        viewModelScope.launch {
            try {
                val result = addProductUseCase.execute(CreateProductDTO(storeId, productId, productStoreDTO))
                if(!result) {
                    alertMessage.postValue("Erreur lors de l'ajout du produit.")
                    return@launch
                }
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

    fun deleteProduct(deleteProductDTO: DeleteProductDTO) {
        viewModelScope.launch {
            try {
                val result = deleteProductUseCase.execute(deleteProductDTO)
                if (!result) {
                    alertMessage.postValue("Erreur lors de la suppression du produit.")
                    return@launch
                }
                Log.d("SellerViewModel", "Successfully deleted product: $result")
                alertMessage.postValue("Produit supprimé avec succès du magasin.")
            } catch (e: Exception) {
                Log.e("SellerViewModel", "Error deleting product", e)
                alertMessage.postValue("Erreur lors de la suppression du produit.")
            }
        }
    }

    fun updateProduct(updateProductDTO: UpdateProductDTO) {
        viewModelScope.launch {
            try {
                val result = updateProductUseCase.execute(updateProductDTO)
                if (!result) {
                    alertMessage.postValue("Erreur lors de la mise à jour du produit.")
                    return@launch
                }
                Log.d("SellerViewModel", "Successfully updated product: $result")
                alertMessage.postValue("Produit mis à jour avec succès.")
            } catch (e: Exception) {
                Log.e("SellerViewModel", "Error updating product", e)
                alertMessage.postValue("Erreur lors de la mise à jour du produit.")
            }
        }
    }

    fun reserveProduct(storeId: Int, userId: Int, reserveDTO: ReserveDTO) {
        viewModelScope.launch {
            try {
                val result  = reserveProductUseCase.execute(storeId, userId, reserveDTO)
                if(!result) {
                    alertMessage.postValue("Erreur lors de la réservation du produit.")
                    return@launch
                }
                alertMessage.postValue("Produit réservé avec succès.")
            } catch (e: Exception) {
                Log.e("SellerViewModel", "Error reserving product", e)
                alertMessage.postValue("Erreur lors de la réservation du produit.")
            }
        }
    }

    fun unreserveProduct(storeId: Int, userId: Int, reserveId: Int) {
        viewModelScope.launch {
            try {
                val result = unreserveProductUseCase.execute(storeId, userId, reserveId)
                if(!result) {
                    alertMessage.postValue("Erreur lors de l'annulation de la réservation du produit.")
                    return@launch
                }
                alertMessage.postValue("Réservation annulée avec succès.")
            } catch (e: Exception) {
                Log.e("SellerViewModel", "Error unreserving product", e)
                alertMessage.postValue("Erreur lors de l'annulation de la réservation du produit.")
            }
        }
    }

    // Implement other actions (e.g., updateProduct, deleteProduct) similarly
}

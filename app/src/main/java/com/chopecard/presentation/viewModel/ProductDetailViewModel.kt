package com.chopecard.presentation.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.usecases.GetProductDetailUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProductDetailViewModel(private val getProductDetailUseCase: GetProductDetailUseCase) : ViewModel() {

    fun loadProductDetail(productId: Int) {
        viewModelScope.launch {
            try {
                val product = getProductDetailUseCase.execute(productId)
                // Mettez à jour l'UI avec les détails du produit récupéré
            } catch (e: HttpException) {
                // Gérer les erreurs HTTP
            } catch (e: Exception) {
                // Gérer les autres erreurs
            }
        }
    }
}

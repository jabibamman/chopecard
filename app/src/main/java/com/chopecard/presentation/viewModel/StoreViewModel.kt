package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.models.Store
import com.chopecard.domain.usecases.GetStoresUseCase
import kotlinx.coroutines.launch

/** Represents the UI state. */
sealed class StoreDataState {
    object Loading : StoreDataState()
    data class Success(val stores: List<Store>): StoreDataState()
    data class Error(val ignoredException: Throwable): StoreDataState()
}

class StoreViewModel(
    private val getStoresUseCase: GetStoresUseCase,
) : ViewModel() {

    val storesLiveData = MutableLiveData<StoreDataState>()
    fun getStores() {
        storesLiveData.postValue(StoreDataState.Loading)
        viewModelScope.launch {
            try {
                val stores = getStoresUseCase.execute()
                storesLiveData.postValue(StoreDataState.Success(stores))
                Log.d("StoreViewModel", "Stores: $stores")
            } catch (e: Exception) {
                storesLiveData.postValue(StoreDataState.Error(e))
            }
        }
    }




}

package com.chopecard.data.repository.impl

import android.util.Log
import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.model.DeleteProductDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.StoreDTO
import com.chopecard.data.model.UpdateProductDTO
import com.chopecard.data.network.StoreApiService
import com.chopecard.data.repository.StoreRepository
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.UserReservation

class StoreRepositoryImpl(private val storeApiService: StoreApiService) : StoreRepository {
    override suspend fun getStores(): List<Store> {
        return try {
            val response = storeApiService.getStores()
            if (response.isSuccessful) {
                // Log succès
                Log.d("StoreRepositoryImpl", "Stores: ${response.body().toString()}")
                response.body() ?: emptyList()
            } else {
                Log.e("StoreRepositoryImpl", "Error getting stores: HTTP ${response.code()} ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            // Dans le bloc catch, vous n'avez pas accès à la variable response
            // Vous devez donc enregistrer l'exception sans essayer d'accéder à response
            Log.e("StoreRepositoryImpl", "Exception when calling API", e)
            emptyList()
        }
    }

    override suspend fun createStore(storeDTO: StoreDTO): String {
        val storeDTO = storeApiService.createStore(storeDTO)
        return try {
            val response = storeDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getStore(storeId: Int): Store {
        val storeDTO = storeApiService.getStoresById(storeId)
        return try {
            val response = storeDTO.execute()
            response.body() ?: Store(0, "", "", List<ProductStore>(0) { ProductStore(0, Product(0, "", "", 0.0f, 0.0f), 0, 0.0f) })
        } catch (e: Exception) {
            Store(0, "", "", List<ProductStore>(0) { ProductStore(0, Product(0, "", "", 0.0f, 0.0f), 0, 0.0f) })
        }
    }

    override suspend fun deleteStore(storeId: Int): String {
        val storeDTO = storeApiService.deleteStore(storeId)
        return try {
            val response = storeDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getStoreProducts(storeId: Int): List<ProductStore> {
        val productStoreDTOs = storeApiService.getStoreProducts(storeId)
        return try {
            val response = productStoreDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun reserveProduct(storeId: Int, userId: Int, reserveDTO: ReserveDTO): String {
        val reserveDTO = storeApiService.reserveProduct(storeId, userId, reserveDTO)
        return try {
            val response = reserveDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun unreserveProduct(storeId: Int, userId: Int, reserveId: Int): String {
        val reserveDTO = storeApiService.unreserveProduct(storeId, userId, reserveId)
        return try {
            val response = reserveDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getProduct(storeId: Int, productId: Int): ProductStore {
        val productStoreDTO = storeApiService.getProduct(storeId, productId)
        return try {
            val response = productStoreDTO.execute()
            response.body() ?: ProductStore(0, Product(0, "", "", 0.0f, 0.0f), 0, 0.0f)
        } catch (e: Exception) {
            ProductStore(0, Product(0, "", "", 0.0f, 0.0f), 0, 0.0f)
        }
    }

    override suspend fun createProduct(
       createProductDTO: CreateProductDTO
    ) {
        try {
            val response = storeApiService.createProduct(createProductDTO.storeId, createProductDTO.productId, createProductDTO.productStoreDTO)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Successfully created product")
            } else {
                Log.e("StoreRepositoryImpl", "Error creating product: HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("StoreRepositoryImpl", "Exception when calling API", e)
        }

    }

    override suspend fun deleteProductStore(deleteProductDTO: DeleteProductDTO) {
        try {
            val response = storeApiService.deleteProduct(deleteProductDTO.storeId, deleteProductDTO.productId)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Successfully deleted product")
            } else {
                Log.e("StoreRepositoryImpl", "Error deleting product: HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("StoreRepositoryImpl", "Exception when calling API", e)
        }
    }

override suspend fun updateProductStore(
        updateProductDTO: UpdateProductDTO
    ) {
        try {
            val response = storeApiService.updateProduct(updateProductDTO.storeId, updateProductDTO.productId, updateProductDTO.productStoreDTO)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Successfully updated product")
            } else {
                Log.e("StoreRepositoryImpl", "Error updating product: HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("StoreRepositoryImpl", "Exception when calling API", e)
        }
    }

    override suspend fun getReserves(storeId: Int): List<UserReservation> {
        val userReservationDTOs = storeApiService.getReserves(storeId)
        return try {
            val response = userReservationDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getDisplay(storeId: Int): Display {
        val displayDTO = storeApiService.getDisplay(storeId)
        return try {
            val response = displayDTO.execute()
            response.body() ?: Display(0, "", "", 0.0f, 0.0f)
        } catch (e: Exception) {
            Display(0, "", "", 0.0f, 0.0f)
        }
    }

}

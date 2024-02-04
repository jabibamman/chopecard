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
                Log.d("StoreRepositoryImpl", "Stores: ${response.body().toString()}")
                response.body() ?: emptyList()
            } else {
                Log.e("StoreRepositoryImpl", "Error getting stores: HTTP ${response.code()} ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("StoreRepositoryImpl", "Exception when calling API", e)
            emptyList()
        }
    }

    override suspend fun createStore(storeDTO: StoreDTO): String {
        return try {
            val response = storeApiService.createStore(storeDTO)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Store created: ${response.body()}")
                response.body() ?: String()
            } else {
                Log.e("StoreRepositoryImpl", "Error creating store: HTTP ${response.code()} ${response.message()}")
                String()
            }
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getStore(storeId: Int): Store {
        val basicStore = Store(0, "", "", List<ProductStore>(0) { ProductStore(0, Product(0, "", "", 0.0f, 0.0f), 0, 0.0f) })
        return try {
            val response = storeApiService.getStoresById(storeId)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Store: ${response.body()}")
            } else {
                Log.e("StoreRepositoryImpl", "Error getting store: HTTP ${response.code()} ${response.message()}")
            }
            response.body() ?: basicStore
        } catch (e: Exception) {
            basicStore
        }
    }

    override suspend fun deleteStore(storeId: Int): String {
        return try {
            val response = storeApiService.deleteStore(storeId)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Store deleted: ${response.body()}")
            } else {
                Log.e("StoreRepositoryImpl", "Error deleting store: HTTP ${response.code()} ${response.message()}")
            }
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getStoreProducts(storeId: Int): List<ProductStore> {
        return try {
            val response = storeApiService.getStoreProducts(storeId)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Products: ${response.body()}")
            } else {
                Log.e("StoreRepositoryImpl", "Error getting products: HTTP ${response.code()} ${response.message()}")
            }
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun reserveProduct(storeId: Int, userId: Int, reserveDTO: ReserveDTO): String {
        return try {
            val response = storeApiService.reserveProduct(storeId, userId, reserveDTO)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Successfully reserved product")
            } else {
                Log.e("StoreRepositoryImpl", "Error reserving product: HTTP ${response.code()} ${response.message()}")
            }
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun unreserveProduct(storeId: Int, userId: Int, reserveId: Int): String {
        return try {
            val response = storeApiService.unreserveProduct(storeId, userId, reserveId)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Successfully unreserved product")
            } else {
                Log.e("StoreRepositoryImpl", "Error unreserving product: HTTP ${response.code()} ${response.message()}")
            }
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getProduct(storeId: Int, productId: Int): ProductStore {
        val basicProductStore = ProductStore(0, Product(0, "", "", 0.0f, 0.0f), 0, 0.0f)
        return try {
            val response = storeApiService.getProduct(storeId, productId)
            response.body() ?: basicProductStore
        } catch (e: Exception) {
            basicProductStore
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
        return try {
            val response = storeApiService.getReserves(storeId)
            if (response.isSuccessful) {
                Log.d("StoreRepositoryImpl", "Reserves: ${response.body()}")
            } else {
                Log.e("StoreRepositoryImpl", "Error getting reserves: HTTP ${response.code()} ${response.message()}")
            }
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getDisplay(storeId: Int): Display {
        val basicDisplay = Display(0, "", "", 0.0f, 0.0f)
        return try {
            val response = storeApiService.getDisplay(storeId)
            response.body() ?: basicDisplay
        } catch (e: Exception) {
            basicDisplay
        }
    }

}

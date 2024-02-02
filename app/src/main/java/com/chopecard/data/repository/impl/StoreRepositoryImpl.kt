package com.chopecard.data.repository.impl

import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.StoreDTO
import com.chopecard.data.network.StoreApiService
import com.chopecard.data.repository.StoreRepository
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.UserReservation
import retrofit2.Call

class StoreRepositoryImpl(private val storeApiService: StoreApiService) : StoreRepository {
    override suspend fun getStores(): List<Store> {
        val storeDTOs: Call<List<Store>> = storeApiService.getStores()
        return try {
            val response = storeDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
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
            response.body() ?: Store(0, "", "", List<ProductStore>(0) { ProductStore(0, "", 0, 0.0f) })
        } catch (e: Exception) {
            Store(0, "", "", List<ProductStore>(0) { ProductStore(0, "", 0, 0.0f) })
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
            response.body() ?: ProductStore(0, "", 0, 0.0f)
        } catch (e: Exception) {
            ProductStore(0, "", 0, 0.0f)
        }
    }

    override suspend fun createProduct(
       createProductDTO: CreateProductDTO
    ): String {
        val productStoreDTO = storeApiService.createProduct(createProductDTO.storeId, createProductDTO.productId, createProductDTO.productStoreDTO)
        return try {
            val response = productStoreDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
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

package com.chopecard.data.repository

import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.StoreDTO
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.UserReservation
import retrofit2.Call

interface StoreRepository {

    // Retrieves a list of all stores
    suspend fun getStores(): List<Store>

    // Creates a new store record
    suspend fun createStore(storeDTO: StoreDTO): String

    // Retrieves details of a specific store by store ID
    suspend fun getStore(storeId: Int): Store

    // Deletes a store from the system
    suspend fun deleteStore(storeId: Int): String

    // Retrieves all products available in a specific store
    suspend fun getStoreProducts(storeId: Int): List<ProductStore>

    // Makes a reservation for a product in a store
    suspend fun reserveProduct(storeId: Int, userId: Int, reserveDTO: ReserveDTO): String

    // Cancels a product reservation in a store
    suspend fun unreserveProduct(storeId: Int, userId: Int, reserveId: Int): String

    // Retrieves product details by product ID within a specific store
    suspend fun getProduct(storeId: Int, productId: Int): ProductStore

    // Creates a new product record within a specific store
    suspend fun createProduct(
        storeId: Int,
        productId: Int,
        productStoreDTO: ProductStoreDTO
    ): String

    // Gets a list of all reservations for a specific store
    suspend fun getReserves(storeId: Int): List<UserReservation>

    // Retrieves a display details for a specific store
    suspend fun getDisplay(storeId: Int): Display
}

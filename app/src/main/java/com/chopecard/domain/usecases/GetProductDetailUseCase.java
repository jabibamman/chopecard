package com.chopecard.domain.usecases;

import com.chopecard.data.repository.ProductRepository;
import com.chopecard.domain.models.Product;

class GetProductDetailUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productId: Int): Product {
        return productRepository.getProduct(productId);
    }
}

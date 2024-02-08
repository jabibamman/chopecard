package com.chopecard.ui.activity

import android.os.Bundle
import android.widget.TextView
import com.chopecard.R
import com.chopecard.domain.models.Product
import com.chopecard.presentation.viewModel.ProductDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : BaseActivity(){

    private val productDetailViewModel: ProductDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_layout)
        setupFooter()
        val productId = 1 // ou tout autre moyen de récupérer l'ID du produit
        productDetailViewModel.loadProductDetail(productId)

        productDetailViewModel.productLiveData.observe(this) { product ->
            displayProductDetail(product)
        }

        productDetailViewModel.errorLiveData.observe(this) { error ->
            // Gérer les erreurs ici
        }
    }

    private fun displayProductDetail(product: Product) {
        val productNameTextView = findViewById<TextView>(R.id.product_name)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionproduct)
        val priceMinTextView = findViewById<TextView>(R.id.priceMinproduct)
        val priceMaxTextView = findViewById<TextView>(R.id.priceMaxproduct)

        productNameTextView.text = product.name
        descriptionTextView.text = product.description
        priceMinTextView.text = "Price: ${product.priceMin}"
        priceMaxTextView.text = " - ${product.priceMax}"
    }
}

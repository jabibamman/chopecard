package com.chopecard.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chopecard.R
import com.chopecard.data.model.Card
import com.chopecard.presentation.viewModel.ProductDetailState
import com.chopecard.presentation.viewModel.ProductDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.chopecard.databinding.ProductDetailLayoutBinding

class ProductDetailActivity : BaseActivity() {
    private lateinit var binding: ProductDetailLayoutBinding

    private val productDetailViewModel: ProductDetailViewModel by viewModel()

    private lateinit var productNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var priceMinTextView: TextView
    private lateinit var priceMaxTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFooter()
        initView()
        val cardName = intent.getStringExtra("card_name") ?: ""

        Log.d("ProductDetailActivity", "Card name: $cardName")

        observeProduct()
       productDetailViewModel.loadProductDetail(cardName)
    }

    private fun initView() {
        productNameTextView = findViewById(R.id.tvProductName)
        descriptionTextView = findViewById(R.id.tvDescription)
        priceMinTextView = findViewById(R.id.price_min_product)
        priceMaxTextView = findViewById(R.id.price_max_product)
        imageView = binding.imageViewCard
        progressBar = findViewById(R.id.product_progress_bar)
    }

    private fun observeProduct() {
       productDetailViewModel.productLiveData.observe(this) { productDataState ->
            when (productDataState) {
                is ProductDetailState.Loading -> {
                    findViewById<ProgressBar>(R.id.product_progress_bar).visibility = View.VISIBLE
                }

                is ProductDetailState.Success -> {
                    findViewById<ProgressBar>(R.id.product_progress_bar).visibility = View.GONE
                    displayProductDetail(productDataState.product[0])
                }

                is ProductDetailState.Error -> {
                    findViewById<ProgressBar>(R.id.product_progress_bar).visibility = View.GONE
                    Toast.makeText(this, "Error getting product", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun displayProductDetail(card: Card) {
        productNameTextView.text = card.name
        descriptionTextView.text = card.desc
        priceMinTextView.text = "Min Price: ${card.card_prices[0].amazon_price}"
        priceMaxTextView.text = "Max Price: ${card.card_prices[0].ebay_price}"
        if (card.card_images.isNotEmpty()) {
            Log.d("ProductDetailActivity", "Loading image: ${card.card_images[0].image_url}")
            Glide.with(this)
                .load(card.card_images[0].image_url)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(imageView)
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}

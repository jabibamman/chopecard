    package com.chopecard

    import com.chopecard.ui.activity.CollectorCardListAdapter
    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import android.widget.ProgressBar
    import androidx.appcompat.app.AppCompatDelegate
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.chopecard.data.storage.UserPreferences
    import com.chopecard.databinding.ActivityMainBinding
    import com.chopecard.domain.models.Product
    import com.chopecard.presentation.viewModel.CollectorViewModel
    import com.chopecard.presentation.viewModel.ProductDataState
    import com.chopecard.ui.activity.BaseActivity
    import org.koin.androidx.viewmodel.ext.android.viewModel

    class MainActivity : BaseActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var cardList: List<Product>
        private val  collectorViewModel: CollectorViewModel by viewModel()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupFooter()
            setupRecyclerView()
            observeProducts()
            binding.tvWelcome.text = getString(R.string.welcome, UserPreferences.getUserName(this))
            collectorViewModel.getProducts()
        }

        private fun setupRecyclerView() {
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            val adapter = CollectorCardListAdapter(mutableListOf(), this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }

        private fun observeProducts() {
            collectorViewModel.productLiveData.observe(this) { productDataState ->
                when (productDataState) {
                    is ProductDataState.Loading -> {
                        findViewById<ProgressBar>(R.id.productsprogressBar).visibility = View.VISIBLE
                    }

                    is ProductDataState.Success -> {
                        findViewById<ProgressBar>(R.id.productsprogressBar).visibility = View.GONE
                        val adapter =
                            findViewById<RecyclerView>(R.id.recyclerView).adapter as? CollectorCardListAdapter
                        adapter?.updateList(productDataState.products)
                    }

                    is ProductDataState.Error -> {
                        findViewById<ProgressBar>(R.id.productsprogressBar).visibility = View.GONE
                        Log.e("MainActivity", "Error getting products", productDataState.exception)
                    }
                }
            }
        }
    }

package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.presentation.view.SellerView
import com.chopecard.presentation.viewModel.StoreDataState
import com.chopecard.presentation.viewModel.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopListActivity : BaseActivity() {

    private val storeViewModel: StoreViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        setupFooter()
        setupRecyclerView()
        observeStores()
        storeViewModel.getStores()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvShopList)
        val adapter = ShopListAdapter(mutableListOf()) { store ->
            val intent = Intent(this, SellerView::class.java).apply {
                putExtra("store", store)

            }
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun observeStores() {
        storeViewModel.storesLiveData.observe(this) { storeDataState ->
            when (storeDataState) {
                is StoreDataState.Loading -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
                is StoreDataState.Success -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    val stores = storeDataState.stores
                    (findViewById<RecyclerView>(R.id.rvShopList).adapter as? ShopListAdapter)?.updateList(stores)
                    Log.d("ShopListActivity", "Stores: $stores")
                }
                is StoreDataState.Error -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    Log.e("ShopListActivity", "Error getting stores", storeDataState.exception)
                }
            }
        }
    }

}

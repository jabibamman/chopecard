package com.chopecard.presentation.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.presentation.viewModel.StoreDataState
import com.chopecard.presentation.viewModel.StoreViewModel
import com.chopecard.ui.activity.SellerActivity
import com.chopecard.ui.adapter.ShopListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopListFragment : Fragment() {
    private val storeViewModel: StoreViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ShopListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        observeStores()
        storeViewModel.getStores()
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.rvShopList)
        adapter = ShopListAdapter(mutableListOf()) { store ->
            val intent = Intent(activity, SellerActivity::class.java).apply {
                putExtra("store", store)
            }
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun observeStores() {
        storeViewModel.storesLiveData.observe(viewLifecycleOwner) { storeDataState ->
            when (storeDataState) {
                is StoreDataState.Loading -> progressBar.visibility = View.VISIBLE
                is StoreDataState.Success -> {
                    progressBar.visibility = View.GONE
                    val stores = storeDataState.stores
                    adapter.updateList(stores)
                }
                is StoreDataState.Error -> {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressBar = activity?.findViewById<ProgressBar>(R.id.progressBar) ?: throw IllegalStateException("Activity cannot be null")
    }
}

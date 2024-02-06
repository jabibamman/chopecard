package com.chopecard.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.chopecard.R
import com.chopecard.domain.usecases.GetProductDetailUseCase
import com.chopecard.presentation.fragment.ShopListFragment
import com.chopecard.presentation.viewModel.ProductDetailViewModel

class ProductDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_layout)
        setupFooter()

        val listProducts = ProductDetailViewModel.loadProductDetail(R.id.etProductId)


    }

}

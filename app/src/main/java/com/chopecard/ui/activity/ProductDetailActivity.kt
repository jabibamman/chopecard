package com.chopecard.ui.activity

import android.os.Bundle
import com.chopecard.R

class ProductDetailActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_layout)
        setupFooter()
    }


}

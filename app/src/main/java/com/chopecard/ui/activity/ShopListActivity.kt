package com.chopecard.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.chopecard.R
import com.chopecard.presentation.fragment.ShopListFragment

class ShopListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        setupFooter()
        if (savedInstanceState == null) {
            val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragmentContainerView)
                ?: throw IllegalStateException("Fragment container not found in the layout")

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ShopListFragment())
                .commit()
        }
    }
}

package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.FragmentContainerView
import com.chopecard.R
import com.chopecard.data.storage.UserPreferences
import com.chopecard.presentation.fragment.ShopListFragment

class ShopListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        setupFooter()
        setupListener()
        if (savedInstanceState == null) {
            val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragmentContainerView)
                ?: throw IllegalStateException("Fragment container not found in the layout")

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ShopListFragment())
                .commit()
        }
    }

    private fun setupListener() {
        findViewById<ImageButton>(R.id.btnLogout)?.setOnClickListener {
            Log.d("ShopListActivity", "Logout button clicked")
            UserPreferences.clearAllPreference(this)
            val intent = Intent(this, LoginRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

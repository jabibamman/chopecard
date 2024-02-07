package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.chopecard.MainActivity
import com.chopecard.R
import com.chopecard.data.storage.UserPreferences

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()
    }

    protected fun setupFooter() {
        val userRole = UserPreferences.getUserLogin(this).first
        if (userRole == "ADMIN") {
            val adminButton = findViewById<ImageButton>(R.id.btnNearby)
            adminButton.visibility = android.view.View.VISIBLE
            adminButton.setOnClickListener {
                Log.d("FooterActivity", "Admin button clicked")
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        findViewById<ImageButton>(R.id.btnShops)?.setOnClickListener {
            Log.d("FooterActivity", "Shops button clicked")
            val intent = Intent(this, ShopListActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<ImageButton>(R.id.btnHome)?.setOnClickListener {
            Log.d("FooterActivity", "Home button clicked")
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}

package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.chopecard.MainActivity
import com.chopecard.R

open class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ne pas définir le setContentView ici, car cela sera spécifique à chaque activité enfant
    }

    protected fun setupFooter() {
        findViewById<ImageButton>(R.id.btnShops)?.setOnClickListener {
            Log.d("FooterActivity", "Shops button clicked")

            val intent = Intent(this, ShopListActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.btnHome)?.setOnClickListener {
            Log.d("FooterActivity", "Home button clicked")
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}

package com.chopecard.ui.activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.chopecard.MainActivity
import com.chopecard.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()
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

        findViewById<ImageButton>(R.id.btnNearby)?.setOnClickListener {
            Log.d("FooterActivity", "Profile button clicked")
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)

        }
        findViewById<ImageButton>(R.id.btnFavorites)?.setOnClickListener {
            Log.d("FooterActivity", "Profile button clicked")
            val intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)

        }
    }
}

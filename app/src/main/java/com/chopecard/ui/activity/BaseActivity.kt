package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.chopecard.R

open class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ne pas définir le setContentView ici, car cela sera spécifique à chaque activité enfant
    }

    protected fun setupFooter() {
        findViewById<ImageButton>(R.id.btnShops)?.setOnClickListener {
            val intent = Intent(this, ShopListActivity::class.java)
            startActivity(intent)
        }
    }
}

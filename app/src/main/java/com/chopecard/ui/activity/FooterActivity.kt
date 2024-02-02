package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.chopecard.R

class FooterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.footer_layout)

        // Trouver le bouton dans le footer par son ID et définir un OnClickListener
        findViewById<ImageButton>(R.id.btnShops).setOnClickListener {
            val intent = Intent(this, ShopListActivity::class.java)
            startActivity(intent)
        }
    }
}
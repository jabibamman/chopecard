package com.chopecard.ui.activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.chopecard.MainActivity
import com.chopecard.R
import com.chopecard.data.storage.UserPreferences
import com.chopecard.domain.models.ERole
import com.chopecard.ui.utils.ActivityName
import com.chopecard.ui.utils.CurrentActivityTracker

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()
    }

    protected fun setupFooter() {
        val userRole = UserPreferences.getUserLogin(this).first
        if (userRole == ERole.ADMIN.name) {
            val adminButton = findViewById<ImageButton>(R.id.btnAdmin)
            adminButton.visibility = android.view.View.VISIBLE
            if (CurrentActivityTracker.getCurrentActivity() != ActivityName.AdminActivity) {
                adminButton.setOnClickListener {
                    Log.d("FooterActivity", "Admin button clicked")
                    CurrentActivityTracker.setCurrentActivity(ActivityName.AdminActivity)
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        if (CurrentActivityTracker.getCurrentActivity() != ActivityName.ShopListActivity) {
            findViewById<ImageButton>(R.id.btnShops)?.setOnClickListener {
                Log.d("FooterActivity", "Shops button clicked")
                CurrentActivityTracker.setCurrentActivity(ActivityName.ShopListActivity)
                val intent = Intent(this, ShopListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        if (CurrentActivityTracker.getCurrentActivity() != ActivityName.MainActivity) {
            findViewById<ImageButton>(R.id.btnCards)?.setOnClickListener {
                Log.d("FooterActivity", "Card button clicked")
                CurrentActivityTracker.setCurrentActivity(ActivityName.MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        }

        findViewById<ImageButton>(R.id.btnLogout)?.setOnClickListener {
            Log.d("ShopListActivity", "Logout button clicked")
            CurrentActivityTracker.setCurrentActivity(ActivityName.LoginRegisterActivity)
            UserPreferences.clearAllPreference(this)
            val intent = Intent(this, LoginRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

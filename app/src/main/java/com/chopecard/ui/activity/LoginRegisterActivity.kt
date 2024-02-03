package com.chopecard.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.chopecard.MainActivity
import com.chopecard.R
import com.chopecard.data.model.UserDTO
import com.chopecard.data.storage.UserPreferences
import com.chopecard.presentation.viewModel.LoginViewModel
import com.chopecard.presentation.viewModel.UserCreationResult
import com.chopecard.ui.utils.showAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginRegisterActivity : AppCompatActivity() {
    private val loginViewModel : LoginViewModel by viewModel()
    private lateinit var etName: EditText
    private lateinit var etMail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LoginRegisterActivity", "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        etName = findViewById(R.id.etName)
        etMail = findViewById(R.id.etMail)

        loginViewModel.userData.observe(this) { user ->
            if (user.userId > 0) {
                UserPreferences.saveUserLogin(this, user.userId)
                UserPreferences.setWelcomeShown(this)
                navigateTo(MainActivity::class.java)
            }
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            if (etName.visibility == View.VISIBLE) {
                etName.visibility = View.GONE
                etMail.text.clear()
                etMail.requestFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etMail, InputMethodManager.SHOW_IMPLICIT)
            } else {
                Log.d("LoginRegisterActivity", "Logging in user")
                performLogin()
            }
        }

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val etName = findViewById<EditText>(R.id.etName)
            if (etName.visibility == View.GONE) {
                etName.visibility = View.VISIBLE
                etName.text.clear()
                etName.requestFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etName, InputMethodManager.SHOW_IMPLICIT)
            } else {
                Log.d("LoginRegisterActivity", "Registering user")
                performRegistration()
            }
        }

        loginViewModel.userCreationResult.observe(this) { result ->
            when (result) {
                is UserCreationResult.Success -> {
                    val user = result.user
                    UserPreferences.saveUserLogin(this, user.userId)
                    UserPreferences.setWelcomeShown(this)
                    navigateTo(MainActivity::class.java)
                }
                is UserCreationResult.Failure -> {
                    showAlert("Registration failed: ${result.error.message}", this)
                }
            }
        }

        Log.d("LoginRegisterActivity", "Your userPreferences: ${UserPreferences.getUserLogin(this)}")
    }


    private fun navigateTo(clasZ: Class<*>) {
        val intent = Intent(this, clasZ)
        startActivity(intent)
        finish()
    }

    private fun performLogin() {
        val username = findViewById<EditText>(R.id.etName).text.toString()
        val email = findViewById<EditText>(R.id.etMail).text.toString()

        loginViewModel.loginUser(email)
        Log.d("LoginRegisterActivity", "Attempting login with email: $email")

        loginViewModel.userData.observe(this, Observer { user ->
            if (user.userId > 0) {
                UserPreferences.saveUserLogin(this, user.userId)
                UserPreferences.setWelcomeShown(this)
                navigateTo(MainActivity::class.java)
            } else {
                showAlert("Invalid credentials", this)
            }
        })

    }

    private fun performRegistration() {
        val name = findViewById<EditText>(R.id.etName).text.toString()
        val email = findViewById<EditText>(R.id.etMail).text.toString()

        val user = UserDTO(name, email)
        Log.d("LoginRegisterActivity", "Attempting registration with email: $email")

        loginViewModel.createUser(user)

        Log.d("LoginRegisterActivity", "User created")

        loginViewModel.userCreationResult.observe(this, Observer { result ->
            when (result) {
                is UserCreationResult.Success -> {
                    Log.d("LoginRegisterActivity", "Registration success. Updating UserPreferences...")

                    UserPreferences.saveUserLogin(this, result.user.userId)
                    UserPreferences.setWelcomeShown(this)

                    Log.d("LoginRegisterActivity", "Your userPreferences: ${UserPreferences.getUserLogin(this)}")
                    navigateTo(MainActivity::class.java)
                }
                is UserCreationResult.Failure -> {
                    Log.d("LoginRegisterActivity", "Registration failed")
                    showAlert("Registration failed", this)
                }
            }
        })
    }

}


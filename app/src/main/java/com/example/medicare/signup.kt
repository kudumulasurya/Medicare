package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        val fullNameLayout = findViewById<TextInputLayout>(R.id.FullName)
        val emailLayout = findViewById<TextInputLayout>(R.id.Email)
        val passwordLayout = findViewById<TextInputLayout>(R.id.Password)
        val numberLayout = findViewById<TextInputLayout>(R.id.Number)

        // Set hints dynamically
        fullNameLayout.hint = "Full Name"
        emailLayout.hint = "Email"
        passwordLayout.hint = "Password"
        numberLayout.hint = "Number"

        // Get EditText inside TextInputLayouts
        val fullNameInput = fullNameLayout.editText as TextInputEditText
        val emailInput = emailLayout.editText as TextInputEditText
        val passwordInput = passwordLayout.editText as TextInputEditText
        val numberInput = numberLayout.editText as TextInputEditText

        val signupButton = findViewById<Button>(R.id.button)
        val loginText = findViewById<TextView>(R.id.Login)

        signupButton.setOnClickListener {
            val fullName = fullNameInput.text?.toString()?.trim()
            val email = emailInput.text?.toString()?.trim()
            val password = passwordInput.text?.toString()?.trim()
            val number = numberInput.text?.toString()?.trim()

            var isValid = true

            if (fullName.isNullOrEmpty()) {
                fullNameLayout.error = "Enter Full Name"
                isValid = false
            } else {
                fullNameLayout.error = null
            }

            if (email.isNullOrEmpty()) {
                emailLayout.error = "Enter Email"
                isValid = false
            } else {
                emailLayout.error = null
            }

            if (password.isNullOrEmpty()) {
                passwordLayout.error = "Enter Password"
                isValid = false
            } else {
                passwordLayout.error = null
            }

            if (number.isNullOrEmpty()) {
                numberLayout.error = "Enter Number"
                isValid = false
            } else {
                numberLayout.error = null
            }

            if (isValid) {
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                finish()
            }
        }

        loginText.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
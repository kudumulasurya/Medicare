package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val emailField = findViewById<TextInputEditText>(R.id.Name)
        val passwordField = findViewById<TextInputEditText>(R.id.Email)
        val loginButton = findViewById<Button>(R.id.button)
        val signUpText = findViewById<TextView>(R.id.Login)


        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (validateInput(email, password)) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, home::class.java))
                finish()
            }
        }


        signUpText.setOnClickListener {
            startActivity(Intent(this, signup::class.java))
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (TextUtils.isEmpty(email)) {
            showToast("Email is required")
            return false
        }

        if (TextUtils.isEmpty(password)) {
            showToast("Password is required")
            return false
        }

        if (password.length < 6) {
            showToast("Password must be at least 6 characters")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
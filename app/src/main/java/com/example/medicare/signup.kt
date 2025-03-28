package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        val fullName = findViewById<TextInputEditText>(R.id.Name)
        val email = findViewById<TextInputEditText>(R.id.Email)
        val password = findViewById<TextInputEditText>(R.id.password)
        val number = findViewById<TextInputEditText>(R.id.Number)
        val signupButton = findViewById<Button>(R.id.button)
        val loginText = findViewById<TextView>(R.id.Login)

        // Signup Button Click
        signupButton.setOnClickListener {
            if (validateInput(fullName, email, password, number)) {
                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, home::class.java) // Navigate to Home
                startActivity(intent)
                finish()
            }
        }

        // Login Text Click
        loginText.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(
        fullName: TextInputEditText,
        email: TextInputEditText,
        password: TextInputEditText,
        number: TextInputEditText
    ): Boolean {
        val name = fullName.text.toString().trim()
        val emailText = email.text.toString().trim()
        val passwordText = password.text.toString().trim()
        val numberText = number.text.toString().trim()

        if (TextUtils.isEmpty(name)) {
            fullName.error = "Full Name is required"
            showToast("Full Name is required")
            return false
        }

        if (TextUtils.isEmpty(emailText) || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.error = "Enter a valid email"
            showToast("Enter a valid email")
            return false
        }

        if (TextUtils.isEmpty(passwordText) || passwordText.length < 6) {
            password.error = "Password must be at least 6 characters"
            showToast("Password must be at least 6 characters")
            return false
        }

        if (TextUtils.isEmpty(numberText) || numberText.length != 10 || !numberText.matches(Regex("\\d{10}"))) {
            number.error = "Enter a valid 10-digit number"
            showToast("Enter a valid 10-digit number")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
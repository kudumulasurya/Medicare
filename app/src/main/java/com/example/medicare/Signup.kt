package com.example.medicare

import android.content.Intent
import android.os.Bundle
import android.util.Log // Add this import
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Initialize Firebase Auth
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://medicare-c445c-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .reference.child("Users")

        val fullName = findViewById<TextInputEditText>(R.id.Name)
        val email = findViewById<TextInputEditText>(R.id.Email)
        val password = findViewById<TextInputEditText>(R.id.password)
        val number = findViewById<TextInputEditText>(R.id.Number)
        val signupButton = findViewById<Button>(R.id.button)
        val loginText = findViewById<TextView>(R.id.Login)

        signupButton.setOnClickListener {
            val name = fullName.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val numberText = number.text.toString().trim()

            if (validateInput(name, emailText, passwordText, numberText)) {
                registerUser(name, emailText, passwordText, numberText)
            }
        }

        loginText.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    private fun registerUser(name: String, email: String, password: String, number: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        // Save user data to database
                        val userData = User(name, email, number)
                        database.child(user.uid).setValue(userData)
                            .addOnSuccessListener {
                                Log.d("Signup", "User data saved successfully")
                                navigateToHome()
                            }
                            .addOnFailureListener { e ->
                                Log.e("Signup", "Error saving user data", e)
                                showToast("Account created but failed to save profile data")
                                navigateToHome()
                            }
                    }
                } else {
                    val errorMessage = when {
                        task.exception?.message?.contains("email address is already in use") == true ->
                            "Email already in use. Please login instead."
                        task.exception?.message?.contains("password is invalid") == true ->
                            "Password is too weak. Use at least 6 characters."
                        else -> "Sign up failed: ${task.exception?.message}"
                    }
                    Log.e("Signup", errorMessage, task.exception)
                    showToast(errorMessage)
                }
            }
    }

    private fun navigateToHome() {
        val intent = Intent(this, Navigation::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun validateInput(name: String, email: String, password: String, number: String): Boolean {
        if (name.isEmpty()) {
            showToast("Full name is required")
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Enter a valid email address")
            return false
        }
        if (password.length < 6) {
            showToast("Password must be at least 6 characters")
            return false
        }
        if (!number.matches(Regex("\\d{10}"))) {
            showToast("Enter a valid 10-digit phone number")
            return false
        }
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    data class User(val name: String, val email: String, val phone: String)
}
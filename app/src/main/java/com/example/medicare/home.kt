package com.example.medicare

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class home : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "UseKtx")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT


        setContentView(R.layout.activity_home)
        val cardView = findViewById<CardView>(R.id.card)
        val detailsLayout = findViewById<View>(R.id.details_layout)

        val book1Button = findViewById<Button>(R.id.book1)
        val book2Button = findViewById<Button>(R.id.book2)
        val book3Button = findViewById<Button>(R.id.book3)
        val book4Button = findViewById<Button>(R.id.book4)

        val feverImageView = findViewById<ImageView>(R.id.fever)
        val coldImageView = findViewById<ImageView>(R.id.cold)
        val coughImageView = findViewById<ImageView>(R.id.cough)
        val painImageView = findViewById<ImageView>(R.id.pain)
        val dentalImageView = findViewById<ImageView>(R.id.dental)
        val skinImageView = findViewById<ImageView>(R.id.skin)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        detailsLayout.visibility = View.GONE
        fun showDetails() {
            detailsLayout.visibility = View.VISIBLE
            detailsLayout.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 300 })
        }
        fun hideDetails() {
            detailsLayout.startAnimation(AlphaAnimation(1f, 0f).apply { duration = 300 })
            detailsLayout.postDelayed({ detailsLayout.visibility = View.GONE }, 300)
        }
        cardView.setOnClickListener {
            if (detailsLayout.visibility == View.GONE) showDetails() else hideDetails()
        }
        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN && detailsLayout.visibility == View.VISIBLE) {
                hideDetails()
            }
            false
        }



        book1Button.setOnClickListener {
            navigateToAppointmentPage()
        }
        book2Button.setOnClickListener {
            navigateToAppointmentPage()
        }
        book3Button.setOnClickListener {
            navigateToAppointmentPage()
        }
        book4Button.setOnClickListener {
            navigateToAppointmentPage()
        }


        feverImageView.setOnClickListener {
            navigateToAppointmentPage()
        }
        coldImageView.setOnClickListener {
            navigateToAppointmentPage()
        }
        coughImageView.setOnClickListener {
            navigateToAppointmentPage()
        }
        painImageView.setOnClickListener {
            navigateToAppointmentPage()
        }
        dentalImageView.setOnClickListener {
            navigateToAppointmentPage()
        }
        skinImageView.setOnClickListener {
            navigateToAppointmentPage()
        }


        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navigateToHomePage()
                    true
                }
                R.id.search -> {
                    navigateToSearchPage()
                    true
                }
                R.id.alarm -> {
                    navigateToRemainderPage()
                    true
                }
                R.id.profile -> {
                    navigateToProfilePage()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToAppointmentPage() {
        val intent = Intent(this, appointment::class.java)
        startActivity(intent)
    }

    private fun navigateToSearchPage() {
        val intent = Intent(this, search::class.java)
        startActivity(intent)
    }

    private fun navigateToRemainderPage() {
        val intent = Intent(this, reminder::class.java)
        startActivity(intent)
    }

    private fun navigateToProfilePage() {
        val intent = Intent(this, profile::class.java)
        startActivity(intent)
    }

    private fun navigateToHomePage() {
        val intent = Intent(this, home::class.java)
        startActivity(intent)
    }

}

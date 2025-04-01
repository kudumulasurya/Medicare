package com.example.medicare


import android.graphics.Color

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "UseKtx")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContentView(R.layout.activity_home)
        val cardView = findViewById<CardView>(R.id.card)
        val detailsLayout = findViewById<View>(R.id.details_layout)

        val feverImageView = findViewById<ImageView>(R.id.fever)
        val coldImageView = findViewById<ImageView>(R.id.cold)
        val coughImageView = findViewById<ImageView>(R.id.cough)
        val painImageView = findViewById<ImageView>(R.id.pain)
        val dentalImageView = findViewById<ImageView>(R.id.dental)
        val skinImageView = findViewById<ImageView>(R.id.skin)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        detailsLayout.visibility = View.GONE
        bottomNavView.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)

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

        feverImageView.setOnClickListener { navigateToAppointmentPage() }
        coldImageView.setOnClickListener { navigateToAppointmentPage() }
        coughImageView.setOnClickListener { navigateToAppointmentPage() }
        painImageView.setOnClickListener { navigateToAppointmentPage() }
        dentalImageView.setOnClickListener { navigateToAppointmentPage() }
        skinImageView.setOnClickListener { navigateToAppointmentPage() }

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

        // **RecyclerView Setup**
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val doctorList = mutableListOf(
            DoctorModel(R.drawable.img_10, "Dr. Devaraj Eye Hospital", "Dr. Devaraj M", "Ophthalmologist - 14 years experience", "★★★★★ 45 reviews", "₹300 Consultation Fees"),
            DoctorModel(R.drawable.img_11, "Hope Hospital", "Dr. Krishna Kumar", "Orthopedist - 18 years experience", "★★★★ 31 reviews", "₹400 Consultation Fees"),
            DoctorModel(R.drawable.img_12, "Chisel Dental", "Dr. Deepthi", "Dentist - 21 years experience", "★★★★ 35 reviews", "₹500 Consultation Fees"),
            DoctorModel(R.drawable.img_13, "Children Hospital", "Dr. Mallesh", "Pediatrician - 20 years experience", "★★★★ 20 reviews", "₹300 Consultation Fees")
        )

        val adapter = DoctorAdaptor(doctorList, this)
        recyclerView.adapter = adapter
    }

    private fun navigateToAppointmentPage() {
        val intent = Intent(this, AppointmentActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearchPage() {
        val intent = Intent(this, SearchActivity::class.java)
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
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }
}

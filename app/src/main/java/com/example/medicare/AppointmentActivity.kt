package com.example.medicare

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val doctorImage = intent.getIntExtra("image", 0)
        val hospitalName = intent.getStringExtra("hospital_name")
        val doctorName = intent.getStringExtra("doctor_name")
        val specialty = intent.getStringExtra("experience")
        val rating = intent.getStringExtra("rating")
        val fees = intent.getStringExtra("fees")

        val DoctorProfile = findViewById<ImageView>(R.id.ivDoctorProfile)
        val Hospital = findViewById<TextView>(R.id.hospital)
        val DoctorName = findViewById<TextView>(R.id.docname)
        val Specialty = findViewById<TextView>(R.id.specialist)
        val Rating = findViewById<TextView>(R.id.rating)
        val Fees = findViewById<TextView>(R.id.tvConsultationFee)
        val ChangeDoctor = findViewById<Button>(R.id.btnChangeDoctor)
        val Back = findViewById<ImageView>(R.id.Back)
        Back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        DoctorProfile.setImageResource(doctorImage)
        Hospital.text = hospitalName
        DoctorName.text = doctorName
        Specialty.text = specialty
        Rating.text = rating
        Fees.text = fees


        ChangeDoctor.setOnClickListener {
            finish()
        }
    }
}

package com.example.medicare

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        val switch = findViewById<Switch>(R.id.switchNotifications)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switch.trackDrawable.setTint(ContextCompat.getColor(this, R.color.track_on))
                switch.thumbDrawable.setTint(ContextCompat.getColor(this, R.color.thumb_on))
            } else {
                switch.trackDrawable.setTint(ContextCompat.getColor(this, R.color.track_off))
                switch.thumbDrawable.setTint(ContextCompat.getColor(this, R.color.thumb_off))
            }
        }


    }
}
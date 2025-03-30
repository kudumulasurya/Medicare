package com.example.medicare

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // 🔥 Set Firebase Realtime Database URL Globally
        FirebaseDatabase.getInstance("https://medicare-c445c-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .setPersistenceEnabled(true)
    }
}

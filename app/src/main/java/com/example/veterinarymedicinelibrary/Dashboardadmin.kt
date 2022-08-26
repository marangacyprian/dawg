package com.example.veterinarymedicinelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinarymedicinelibrary.databinding.ActivityComicsBinding
import com.example.veterinarymedicinelibrary.databinding.ActivityDashboardadminBinding
import com.google.firebase.auth.FirebaseAuth

class Dashboardadmin : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardadminBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardadminBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backbtn.setOnClickListener {
            onBackPressed()
        }
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun checkUser() {
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser == null){
            binding.subtitleIv.text="not logged in"
        }else{
            val email=firebaseUser.email
            binding.subtitleIv.text=email
        }

    }
}
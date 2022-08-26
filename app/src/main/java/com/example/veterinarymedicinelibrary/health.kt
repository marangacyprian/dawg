package com.example.veterinarymedicinelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinarymedicinelibrary.databinding.ActivityComicsBinding
import com.example.veterinarymedicinelibrary.databinding.ActivityHealthBinding
import com.google.firebase.auth.FirebaseAuth

class health : AppCompatActivity() {
    private lateinit var binding: ActivityHealthBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthBinding.inflate(layoutInflater)
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
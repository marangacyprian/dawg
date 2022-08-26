package com.example.veterinarymedicinelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinarymedicinelibrary.databinding.*
import com.google.firebase.auth.FirebaseAuth

class politicalscienceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPoliticalscienceBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoliticalscienceBinding.inflate(layoutInflater)
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
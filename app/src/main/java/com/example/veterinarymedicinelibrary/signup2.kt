package com.example.veterinarymedicinelibrary

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.veterinarymedicinelibrary.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class signup2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.backbtn.setOnClickListener {
            onBackPressed()
        }
        binding.signbtn.setOnClickListener {
            validateData()
        }
    }

    private var name = ""
    private var email = ""
    private var password = ""


    private fun validateData() {
        name = binding.nameEt.text.toString().trim()
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        val cpassword = binding.cpasswordEt.text.toString().trim()


        if (name.isEmpty()) {
            Toast.makeText(this, "enter your name..", Toast.LENGTH_SHORT).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "invalid email account..", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "enter password..", Toast.LENGTH_SHORT).show()
        } else if (cpassword.isEmpty()) {
            Toast.makeText(this, "confirm password..", Toast.LENGTH_SHORT).show()
        } else if (cpassword != password) {
            Toast.makeText(this, "password dont match..", Toast.LENGTH_SHORT).show()
        } else {
            createUserAccount()
        }
    }

    private fun createUserAccount() {
        progressDialog.setMessage("creating account...")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "failed creating account due to ${e.message}", Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun updateUserInfo() {
        progressDialog.setMessage("saving user info...")
        val timestamp=System.currentTimeMillis()
        val uid=firebaseAuth.uid

        val hashMap:HashMap<String,Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["name"] = name
        hashMap["profile image"] = ""
        hashMap["userType"] = ""
        hashMap["timestamp"] = timestamp

        val ref= FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Account created...", Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this@signup2,Dashboarduser::class.java))
                finish()
            }

            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(this, "failed saving user info due to ${e.message}", Toast.LENGTH_SHORT
                ).show()
            }

    }
}
package com.example.android.radiancex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android.radiancex.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var fAuth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        fAuth = FirebaseAuth.getInstance()

//        if (fAuth.currentUser != null) {
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//            finish()
//        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                binding.etEmail.error = "Email is required"
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                binding.etPassword.error = "Password is required"
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.etPassword.error = "Password must be at least 6 characters long"
                return@setOnClickListener
            }

            binding.progressBar.visibility = View.VISIBLE

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "An unexpected error occurred ${it.exception.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
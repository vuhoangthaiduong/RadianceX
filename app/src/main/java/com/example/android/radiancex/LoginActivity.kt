package com.example.android.radiancex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android.radiancex.databinding.ActivityLoginBinding
import com.example.android.radiancex.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var fAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        fAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.editText?.text.toString().trim()
            val password = binding.etPassword.editText?.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                binding.etEmail.error = "Email is required"
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                binding.etPassword.error = "Password is required"
                return@setOnClickListener
            }

            binding.included.visibility = View.VISIBLE

            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.included.visibility = View.GONE
                    Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    binding.included.visibility = View.GONE
                    Toast.makeText(this, "An unexpected error occurred ${it.exception.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
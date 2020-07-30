package com.example.android.radiancex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android.radiancex.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var fAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()



        binding.btnSignUp.setOnClickListener {
            val firstName = binding.etFirstname.editText?.text.toString().trim()
            val lastName = binding.etLastName.editText?.text.toString().trim()
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

            if (password.length < 6) {
                binding.etPassword.error = "Password must be at least 6 characters long"
                return@setOnClickListener
            }

            binding.included.visibility = View.VISIBLE

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.included.visibility = View.GONE
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
                    val userID: String = fAuth.currentUser!!.uid
                    val documentReference: DocumentReference = fStore.collection("users").document(userID)
                    val user = mutableMapOf<String, String>()
                    user["first_name"] = firstName
                    user["last_name"] = lastName
                    user["email"] = email
                    documentReference.set(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Success", "Successfully inserted $userID into DB")
                        } else {
                            Log.d("Failure", "Failed to insert $userID into DB")
                        }
                    }
                    intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    binding.included.visibility = View.GONE
                    Toast.makeText(this, "An unexpected error occurred ${it.exception.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnGoBackToLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }
}
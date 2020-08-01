package com.example.android.radiancex.repository

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.radiancex.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.ArrayList

class UserRepository {
    private var fAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var fStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    fun getUser(userId: String): LiveData<User> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<User>()
        fStore.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("getUser: ", "DocumentSnapshot data: ${document.data}")
                        data.value = document.toObject(User::class.java)
                    } else {
                        Log.d("getUser: ", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("getUser: ", "get failed with ", exception)
                }

        return data
    }

    fun getCurrentUser(): LiveData<User>? {
        if (fAuth.currentUser != null) {
            return getUser(fAuth.currentUser!!.uid)
        }
        Log.d("getCurrentUser:", "No user is currently logging in")
        return null
    }

    fun getAllUsers(): LiveData<List<User>> {
        val tempData = ArrayList<User>()
        fStore.collection("users").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result!!.isEmpty) Log.d("getUser: ", "No users")
                else {
                    for (document in task.result!!) {
                        val user = document.toObject(User::class.java)
                        tempData.add(user)
                    }
                }
            }
        }
        val data: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
        data.value = tempData
        return data
    }
}

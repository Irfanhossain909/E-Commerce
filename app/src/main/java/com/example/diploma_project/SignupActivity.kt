package com.example.diploma_project

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diploma_project.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseaauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseaauth = FirebaseAuth.getInstance()

        binding.signupBTN.setOnClickListener{
            val email = binding.emailET.text.toString()
            val password = binding.passET.text.toString()

            firebaseaauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
               Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
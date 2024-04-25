package com.example.diploma_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diploma_project.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseaauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseaauth = FirebaseAuth.getInstance()


        binding.logSignBTN.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        binding.loginBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passET.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){

                    firebaseaauth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                        Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this,it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

            }else{
                Toast.makeText(this,"Empty Fields is not Allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()

        if (firebaseaauth.currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
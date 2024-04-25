package com.example.diploma_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diploma_project.databinding.ActivitySignupBinding
import com.example.diploma_project.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseaauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseaauth = FirebaseAuth.getInstance()

        binding.signLoginBTN.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signupBTN.setOnClickListener{
            val email = binding.emailET.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass = binding.conpassET.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()){
                if (password == confirmPass){

                    firebaseaauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            storeData()
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                        Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this,it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Password is not matched", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty Fields is not Allowed", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun storeData() {


        val builder = AlertDialog.Builder(this)
            .setTitle("Loadind....")
            .setMessage("Please Wait")
            .setCancelable(false)
            .create()
        builder.show()

        val preferences = this.getSharedPreferences("user", MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString("userName", binding.userName.text.toString())
        editor.putString("userPhoneNumber", binding.userNumber.text.toString())
        editor.apply()


        val firestore = FirebaseFirestore.getInstance()
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        val data = UserModel(userName = binding.userName.text.toString(),
            userPhoneNumber = binding.userNumber.text.toString(),
            emailsignUp = binding.emailET.text.toString(),
            passwordsignUp = binding.passET.text.toString())


        if (uid != null){

            Firebase.firestore.collection("users").document(uid)
                .set(data).addOnSuccessListener {
                    Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
        }

    }
}
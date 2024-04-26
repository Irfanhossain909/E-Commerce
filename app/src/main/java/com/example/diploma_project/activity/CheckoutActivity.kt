package com.example.diploma_project.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diploma_project.LoginActivity
import com.example.diploma_project.MainActivity
import com.example.diploma_project.R
import com.example.diploma_project.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)


        binding.textView16.setOnClickListener {
            var intent = Intent(this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)



    }
}
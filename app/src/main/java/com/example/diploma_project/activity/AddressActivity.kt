package com.example.diploma_project.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diploma_project.R
import com.example.diploma_project.databinding.ActivityAddressBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddressActivity : AppCompatActivity() {
    private lateinit var binding :ActivityAddressBinding
    private lateinit var preferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.getSharedPreferences("user", MODE_PRIVATE)

        loadUserInfo()

        binding.proceed.setOnClickListener {

            startActivity(Intent(this,CheckoutActivity::class.java))

//            validateData(
//                binding.userName.text.toString(),
//                binding.userPhoneNumber.text.toString(),
//                binding.division.text.toString(),
//                binding.city.text.toString(),
//                binding.address.text.toString(),
//                binding.postCode.text.toString()
//            )
        }

    }

//    private fun validateData(name: String, number: String, division: String,
//                             city: String, address: String, postCode: String) {
//
//        if (name.isEmpty() || number.isEmpty() || division.isEmpty() ||
//            city.isEmpty() || address.isEmpty() || postCode.isEmpty()){
//
//
//            Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show()
//        }else{
//            storeData(name,number,division,city,address,postCode)
//        }
//    }

//    private fun storeData(name: String, number: String, division: String, city: String, address: String, postCode: String) {
//
//        val map = hashMapOf<String, Any>()
//        map["division"] = division
//        map["city"] = city
//        map["address"] = address
//        map["postCode"] = postCode
//        map["userName"] = name
//        map["userPhoneNumber"] = number
//
//        Firebase.firestore.collection("users")
//            .document(preferences.getString("userPhoneNumber", "")!!)
//            .update(map).addOnSuccessListener {
//                startActivity(Intent(this,CheckoutActivity::class.java))
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Something went wrong",Toast.LENGTH_SHORT).show()
//            }
//    }


    private fun loadUserInfo() {
        Firebase.firestore.collection("user")
            .document(preferences.getString("userPhoneNumber", "")!!)
            .get().addOnSuccessListener {
                binding.userName.setText(it.getString("userName"))
                binding.division.setText(it.getString("division"))
                binding.city.setText(it.getString("city"))
                binding.address.setText(it.getString("address"))
                binding.postCode.setText(it.getString("postCode"))
            }
    }
}
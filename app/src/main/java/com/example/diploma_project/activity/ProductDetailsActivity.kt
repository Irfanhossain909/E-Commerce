package com.example.diploma_project.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.diploma_project.R
import com.example.diploma_project.databinding.ActivityProductDetailsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)

        getProductDetails(intent.getStringExtra("id"))
        setContentView(binding.root)

    }

    private fun getProductDetails(proId: String?) {
        Firebase.firestore.collection("products")
            .document(proId!!).get().addOnSuccessListener {
                val list = it.get("productImages") as ArrayList<String>
                binding.textView.text = it.getString("productName")
                binding.textView2.text = it.getString("productSp")
                binding.textView4.text = it.getString("productDescription")

                val SlideList = ArrayList<SlideModel>()
                for (data in list){
                    SlideList.add(SlideModel(data, ScaleTypes.CENTER_CROP))
                }
                binding.imageSlider.setImageList(SlideList)

            }.addOnFailureListener {
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }
}
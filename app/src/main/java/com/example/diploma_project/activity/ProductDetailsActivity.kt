package com.example.diploma_project.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.diploma_project.MainActivity
import com.example.diploma_project.databinding.ActivityProductDetailsBinding
import com.example.diploma_project.roomdb.AppDatabase
import com.example.diploma_project.roomdb.ProductDao
import com.example.diploma_project.roomdb.ProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsActivity<DocumentSnapshot> : AppCompatActivity() {
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
                val name = it.getString("productName")
                val productSp = it.getString("productSp")
                val productDesc = it.getString("productDescription")
                binding.textView.text = name
                binding.textView2.text = productSp
                binding.textView4.text = productDesc


                val SlideList = ArrayList<SlideModel>()
                for (data in list){
                    SlideList.add(SlideModel(data, ScaleTypes.CENTER_CROP))
                }
                binding.imageSlider.setImageList(SlideList)

                cartAction(proId, name, productSp, it.getString("productCoverImg") )

            }.addOnFailureListener {
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }

    private fun cartAction(proId: String, name: String?, productSp: String?, coverImg: String?){

        val productDao = AppDatabase.getInstance(this).productDao()

        if (productDao.inExit(proId) != null){
            binding.textView5.text = "Got to Cart"
        }else{

            binding.textView5.text = "Add to Cart"
        }

        binding.textView5.setOnClickListener {
            if (productDao.inExit(proId) != null){
                openCart()
            }else{

                addToCart(productDao, proId, name, productSp,coverImg)
            }
        }
    }

    private fun addToCart(productDao: ProductDao, proId: String, name: String?, productSp: String?, coverImg: String?) {

        val data = ProductModel(proId, name!!, coverImg!!,productSp!!)
        lifecycleScope.launch (Dispatchers.IO){
            productDao.insertProduct(data)
            binding.textView5.text = "Got to Cart"
        }
    }

    private fun openCart() {
        val preference = this.getSharedPreferences("info", MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart", true)
        editor.apply()

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
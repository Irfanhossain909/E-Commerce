package com.example.diploma_project.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.diploma_project.R
import com.example.diploma_project.activity.AddressActivity
import com.example.diploma_project.activity.CategoryActivity
import com.example.diploma_project.adapter.CartAdapter
import com.example.diploma_project.databinding.FragmentCartBinding
import com.example.diploma_project.roomdb.AppDatabase
import com.example.diploma_project.roomdb.ProductModel

class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentCartBinding.inflate(layoutInflater)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart", false)
        editor.apply()

        val dao = AppDatabase.getInstance(requireContext()).productDao()
        dao.getAllProducts().observe(requireActivity()){
            binding.cartRecycler.adapter = CartAdapter(requireContext(), it)
            
            totalCost(it)
        }

        return binding.root
    }

    private fun totalCost(data: List<ProductModel>?) {

        var total = 0
        for(item in data!!){
            total += item.productSp!!.toInt()
        }

        binding.textView22.text = "Total item in cart is ${data.size}"
        binding.textView23.text = "Total Cost :$total.00 TK"

        binding.checkout.setOnClickListener {
            val intent = Intent(context, AddressActivity::class.java)
            intent.putExtra("totalCost", total)
            startActivity(intent)
        }
    }

}
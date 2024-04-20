package com.example.diploma_project.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diploma_project.activity.ProductDetailsActivity
import com.example.diploma_project.databinding.LayoutProductItemBinding
import diploma_project.ecommerceadmin.model.AddProductModel

class ProductAdapter (val context: Context, val list: ArrayList<AddProductModel>)
    :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

        inner class ProductViewHolder (val binding: LayoutProductItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = list[position]

        Glide.with(context).load(data.productCoverImg).into(holder.binding.imageView5)
        holder.binding.textView7.text = data.productName
        holder.binding.textView8.text = data.productCetegory
        holder.binding.textView9.text = data.productMrp

        holder.binding.button4.text = data.productSp

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id", list[position].productId)
            context.startActivity(intent)

        }
    }
}
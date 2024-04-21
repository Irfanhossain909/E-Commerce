package com.example.diploma_project.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    @NonNull
    val productId: String,
    @ColumnInfo("productName")
    val productName: String,
    @ColumnInfo("productImage")
    val productImages: String,
    @ColumnInfo("productSp")
    val productSp: String,

)

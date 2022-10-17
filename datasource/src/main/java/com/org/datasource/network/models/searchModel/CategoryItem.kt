package com.org.datasource.network.models.searchModel

import com.google.gson.annotations.SerializedName


data class CategoryItem (


  @SerializedName("results") var results : ArrayList<Results> = arrayListOf(),

)
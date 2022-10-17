package com.org.datasource.datasource.model
import com.org.datasource.network.models.searchModel.CategoryItem
import com.org.datasource.network.models.searchModel.Results

data class MainRecycler(var mediaType: String, var categoryItem: MutableList <Results>) {
}
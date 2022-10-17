package com.org.datasource.datasource

import android.util.Log
import com.org.datasource.constant.Constants
import com.org.datasource.datasource.interfaces.ISearch
import com.org.datasource.datasource.model.MainRecycler
import com.org.datasource.network.interfaces.MultiSearchApi
import com.org.datasource.network.models.searchModel.CategoryItem
import com.org.datasource.network.models.searchModel.Results

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Generic lamda for api responses
typealias responseHandler<T> = (onSuccess:T?, onError:String?) -> Unit

class NetworkManager:ISearch {

    override fun <T> multiSearch(search: String, handler: responseHandler<T>) {
        val apiCall =MultiSearchApi.create()
        apiCall.searchMovies(search,Constants.API_KEY).enqueue(object :Callback<CategoryItem>{
            override fun onResponse(call: Call<CategoryItem>, response: Response<CategoryItem>) {
                response.body()?.let {
                   val generatedData = generateData(it)
                    handler.invoke(generatedData as T,null)
                }
            }
            override fun onFailure(call: Call<CategoryItem>, t: Throwable) {
                handler.invoke(null,t.message.let { "Something went wrong"})
            }
        })
    }


    internal fun generateData(data: CategoryItem):MutableList<MainRecycler>  {
        var results:MutableList<MainRecycler> = ArrayList()

        val sortedCategoryResult: MutableList<String> = ArrayList()

        for (i in 0 until data.results.size) {

            sortedCategoryResult.add(data.results[i].mediaType.toString())
        }

        sortedCategoryResult.sortBy { it }

        val sortedOne = sortedCategoryResult.toSet().toList()

        for (i in 0 until sortedOne.size) {

            var sort: MutableList<Results> = ArrayList()
            for (j in 0 until data.results.size) {

                if (sortedOne[i] == data.results[j].mediaType) {
                    sort.add(data.results[j])
                }
            }
            sort = sortByCategories(sortedOne[i],sort)

            results.add(MainRecycler(sortedOne[i], sort))
        }

        return results
    }
    internal fun sortByCategories(category:String,data: MutableList<Results>): MutableList<Results>{

        var sortedList: MutableList<Results>

        sortedList = if (category.lowercase() == "person" || category.lowercase() == "tv") {
            data.sortedBy { it ->
                it.name?.trim()?.lowercase()
            } as MutableList<Results>
        } else {
            data.sortedBy { it ->
                it.title?.trim()?.lowercase()
            } as MutableList<Results>
        }
        return sortedList
    }
}
package com.example.starzplay.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.datasource.datasource.model.MainRecycler
import com.org.datasource.datasource.NetworkManager

class MainViewModel(private val repository: NetworkManager) : ViewModel() {

    var resultsLive = MutableLiveData<MutableList<MainRecycler>>()

    fun getSearchResult(search: String) {

        repository.multiSearch<MutableList<MainRecycler>> (search){ onSuccess, onError ->
            onError?.let {
                Log.d("Error",it)
            }
            onSuccess?.let {
                resultsLive.postValue(it)
                Log.d("onSuccess:", it.toString())
            }
        }
    }
}

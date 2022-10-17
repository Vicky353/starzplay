package com.example.starzplay.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.org.datasource.datasource.NetworkManager
import com.example.starzplay.viewmodel.MainViewModel

class ViewModelFactory : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(NetworkManager()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
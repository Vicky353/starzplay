package com.org.datasource.datasource.interfaces

import com.org.datasource.datasource.responseHandler


interface ISearch {
    fun <T> multiSearch(search: String, handler:responseHandler<T>):Unit
}
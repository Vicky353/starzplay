package com.org.datasource

import android.util.Log
import com.org.datasource.datasource.NetworkManager
import com.org.datasource.datasource.interfaces.ISearch
import com.org.datasource.datasource.model.MainRecycler
import com.org.datasource.network.models.searchModel.CategoryItem
import com.org.datasource.network.models.searchModel.Results
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var SUT: NetworkManager
    lateinit var category: CategoryItem
    lateinit var results: MutableList<Results>

    @Before
    fun setUp() {
        SUT = NetworkManager()
        category = CategoryItem()
        results = ArrayList()

        results.add(Results(id = 31, mediaType = "Movie", title = "ZTest31"))
        results.add(Results(id = 32, mediaType = "Movie", title = "STest32"))
        results.add(Results(id = 33, mediaType = "Movie", title = "ITest33"))
        results.add(Results(id = 33, mediaType = "Movie", title = "PTest33"))
        results.add(Results(id = 33, mediaType = "Movie", title = "YTest33"))
    }

    @Test
    fun test_sorted_data () {
        val data = SUT.sortByCategories("Movie",results)
        assertEquals(data,results.sortedBy { it -> it.title?.trim()?.lowercase() })
    }
}
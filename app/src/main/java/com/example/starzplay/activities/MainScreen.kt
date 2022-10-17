package com.example.starzplay.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starzplay.R
import com.example.starzplay.adapter.MainRecyclerAdapter
import com.example.starzplay.databinding.ActivityMainBinding
import com.org.datasource.datasource.model.MainRecycler
import com.example.starzplay.utils.ViewModelFactory
import com.example.starzplay.viewmodel.MainViewModel

class MainScreen : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private var mainRecyclerAdapter: MainRecyclerAdapter? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.searchView.setIconifiedByDefault(true);
        binding.searchView.setFocusable(true);
        binding.searchView.setIconified(false);
        binding.searchView.requestFocusFromTouch();
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchData(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelFactory().create(MainViewModel::class.java)
        observeSearchData()
    }

    private fun getSearchData(search: String) {
        viewModel.getSearchResult(search)
    }

    private fun observeSearchData() {
        viewModel.resultsLive.observe(this) { data ->
            if (data.count() > 0) {
                binding.errorLabel.visibility = View.GONE
            }
            setMainMediaTypeRecycler(data)
        }
    }
    private fun setMainMediaTypeRecycler(mainRecycler: List<MainRecycler>) {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.mainRecyler.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(this, mainRecycler)
        binding.mainRecyler.adapter = mainRecyclerAdapter
    }

}
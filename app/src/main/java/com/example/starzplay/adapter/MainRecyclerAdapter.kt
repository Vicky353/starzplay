package com.example.starzplay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starzplay.databinding.MainRecyclerRowItemBinding
import com.org.datasource.datasource.model.MainRecycler
import com.org.datasource.network.models.searchModel.Results

class MainRecyclerAdapter(private val context: Context, private val mainRecycler: List<MainRecycler>):
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    class MainViewHolder(val viewBindind:MainRecyclerRowItemBinding): RecyclerView.ViewHolder(viewBindind.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerAdapter.MainViewHolder {
        val binding = MainRecyclerRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return mainRecycler.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.viewBindind.title = mainRecycler[position]


        setCatItemRecycler(holder.viewBindind.catItemRecycler,mainRecycler[position].categoryItem)
    }
    private fun setCatItemRecycler(recyclerView: RecyclerView, categoryItem:List<Results>)
    {
        val itemRecyclerAdapter = CategoryRecyclerAdapter(context, categoryItem)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        recyclerView.adapter = itemRecyclerAdapter
    }
}
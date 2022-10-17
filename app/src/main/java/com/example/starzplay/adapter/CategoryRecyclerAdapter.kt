package com.example.starzplay.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.example.starzplay.R
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starzplay.activities.DetailScreen
import com.example.starzplay.databinding.CatRowItemsBinding
import com.example.starzplay.utils.Constants.Companion.DEFAULT_IMAGE_URL
import com.org.datasource.network.models.searchModel.Results
import com.squareup.picasso.Picasso

class CategoryRecyclerAdapter(private val context: Context, private val categoryItem:List<Results>):
    RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryItemViewHolder>() {
    class CategoryItemViewHolder(val viewBinding: CatRowItemsBinding): RecyclerView.ViewHolder(viewBinding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding = CatRowItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {

            Picasso.get()
                .load("${DEFAULT_IMAGE_URL}${if (categoryItem[position].backdropPath != null) categoryItem[position].backdropPath else if (categoryItem[position].posterPath != null) categoryItem[position].posterPath else if (categoryItem[position].profilePath != null) categoryItem[position].profilePath else "zKWzjKYJFfCeiB57q3r4Bcm.png"}")
                .placeholder(R.drawable.loader).error(R.drawable.no_image)
                .into(holder.viewBinding.itemImg)


        holder.viewBinding.title.text = if (categoryItem[position].title!=null) categoryItem[position].title else categoryItem[position].name
        holder.viewBinding.itemImg.setOnClickListener(View.OnClickListener {

            val intent = Intent(context,DetailScreen::class.java)
            intent.putExtra("Title",if( categoryItem[position].title==null) categoryItem[position].name else categoryItem[position].title)
            intent.putExtra("Description",categoryItem[position].overview)
            intent.putExtra("Image",if (categoryItem[position].backdropPath != null) categoryItem[position].backdropPath else if (categoryItem[position].posterPath != null) categoryItem[position].posterPath else if (categoryItem[position].profilePath != null) categoryItem[position].profilePath else "zKWzjKYJFfCeiB57q3r4Bcm.png")

            if (categoryItem[position].mediaType=="movie" || categoryItem[position].mediaType=="tv" )
            {
                intent.putExtra("isPlayable",true)
            }
            else {
                intent.putExtra("isPlayable",false)
            }

            context.startActivity(intent)
        })
    }
}
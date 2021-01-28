package com.example.apifood.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apifood.R
import com.example.apifood.data.model.Food
import com.example.apifood.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.item_food.view.*

class AdapterHomeFood : RecyclerView.Adapter<AdapterHomeFood.ViewHolder>() {
    private val foods = mutableListOf<Food>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Food>? = null
    fun updateData(food: MutableList<Food>?) {
        food?.let {
            this.foods.clear()
            this.foods.addAll(food)
            notifyDataSetChanged()
        }
    }

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Food>?
    ) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(foods[position])
    }

    override fun getItemCount(): Int = foods.size

    inner class ViewHolder(
        itemView: View,
        private val itemListener: OnItemRecyclerViewClickListener<Food>?
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var listener: OnItemRecyclerViewClickListener<Food>? = null

        fun binData(food: Food) {
            itemView.title.text = food.title
            itemView.description.text = food.description
            listener = itemListener
            getImage(food)
        }

        private fun getImage(food: Food) {
            Glide.with(itemView.context)
                .load(food.imageUrl)
                .into(itemView.imageView)
        }

        override fun onClick(v: View?) {
            listener?.onItemClickListener(foods[adapterPosition])
        }

    }
}
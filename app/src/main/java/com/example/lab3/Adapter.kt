package com.example.lab3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.databinding.ItemBinding
import com.squareup.picasso.Picasso

class Adapter(val list: List<Photos>) : RecyclerView.Adapter<Adapter.AdapterViewHolder>() {
    class AdapterViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photos: Photos) {
            with(binding) {
                txtId.text = "Id: ${photos.id}"
                txtTitle.text = "Title: ${photos.title}"
                Picasso.get().load(photos.thumbnailUrl).into(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
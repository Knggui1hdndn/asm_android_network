package com.example.lab3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.databinding.ItemBai2Binding
import com.example.lab3.databinding.ItemBinding
import com.example.lab3.model.Photos
import com.example.lab3.model.Profile
import com.example.lab3.model.ProfileItem
import com.squareup.picasso.Picasso

class Adapter_bai_2(val list: Profile) : RecyclerView.Adapter<Adapter_bai_2.AdapterViewHolder>() {
    class AdapterViewHolder(val binding: ItemBai2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: ProfileItem) {
            with(binding) {
                val text = "name: ${profile.name} \n email: ${profile.email} \n home: ${profile.phone.home} \n mobile: ${profile.phone.mobile}"
                this.profile.text=text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            ItemBai2Binding.inflate(
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
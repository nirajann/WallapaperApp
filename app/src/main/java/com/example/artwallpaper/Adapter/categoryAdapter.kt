package com.example.artwallpaper.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artwallpaper.CategoryActivity
import com.example.artwallpaper.Model.categoryModel
import com.example.artwallpaper.R
import com.example.artwallpaper.SetWallpaper


class categoryAdapter(val requireContext: Context, val listOfCategory: ArrayList<categoryModel>) : RecyclerView.Adapter<categoryAdapter.bomViewHolder>(){
    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val  ImageView = itemView.findViewById<ImageView>(R.id.cat_img)
        val name = itemView.findViewById<TextView>(R.id.cat_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_cat,parent,false)

        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        holder.name.text = listOfCategory[position].name
        Glide.with(requireContext).load(listOfCategory[position].link).into(holder.ImageView)
        holder.itemView.setOnClickListener{
            val intent =  Intent(requireContext, CategoryActivity::class.java)
            intent.putExtra("link",listOfCategory[position].link)
            intent.putExtra("uid",listOfCategory[position].id)
            intent.putExtra("name",listOfCategory[position].name)
            requireContext.startActivity(intent)
        }



    }

    override fun getItemCount()=listOfCategory.size

}
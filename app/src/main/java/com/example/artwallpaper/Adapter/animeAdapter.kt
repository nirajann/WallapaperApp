package com.example.artwallpaper.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artwallpaper.Model.AboutModel
import com.example.artwallpaper.R
import com.example.artwallpaper.SetWallpaper
import com.makeramen.roundedimageview.RoundedImageView


class animeAdapter(val requireContext: Context, val listBestOfMonth: ArrayList<AboutModel>) : RecyclerView.Adapter<animeAdapter.bomViewHolder>(){
    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val  ImageView = itemView.findViewById<ImageView>(R.id.anime_img)
        val name = itemView.findViewById<TextView>(R.id.anime_name)
        val Author = itemView.findViewById<TextView>(R.id.anime_author)
        val Genra = itemView.findViewById<TextView>(R.id.anime_Genra)
        val desc = itemView.findViewById<TextView>(R.id.anime_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_animeimg,parent,false)

        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        holder.ImageView
        holder.desc.text = listBestOfMonth[position].desc
        holder.Author.text = listBestOfMonth[position].Author
        holder.Genra.text = listBestOfMonth[position].Genra
        Glide.with(requireContext).load(listBestOfMonth[position].link).into(holder.ImageView);
        holder.itemView.setOnClickListener{
            val intent =  Intent(requireContext,SetWallpaper::class.java)
            intent.putExtra("link",listBestOfMonth[position].link)
            intent.putExtra("name",listBestOfMonth[position].name)
            intent.putExtra("desc",listBestOfMonth[position].desc)

            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount()=listBestOfMonth.size

}
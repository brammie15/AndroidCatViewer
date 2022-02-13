package com.turtywurty.cat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CatAdapter(
    private val cats : MutableList<Cat>
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>(){

    class CatViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        var currentCat = cats[position]
//        holder.itemView.apply {
//            val imgcat : ImageView = findViewById<ImageView>(R.id.imgcat)
//            Picasso.get().load(currentCat.url).into(imgcat)
//            println("cat : ${imgcat.toString()}")
//        }
        var tagsString : String = ""
        for(cat in currentCat.tags){
            tagsString += cat.toString() + "\n"
        }
        holder.itemView.findViewById<TextView>(R.id.tvTags).text = tagsString
        Picasso.get().load(currentCat.url).into(holder.itemView.findViewById<ImageView>(R.id.imgcat))
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    fun addCat(cat: Cat) {
        cats.add(cat)
        notifyItemInserted(cats.size - 1)
    }


}
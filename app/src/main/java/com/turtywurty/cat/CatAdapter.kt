package com.turtywurty.cat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CatAdapter(
    private val context: Context,
    public var cats : MutableList<Cat>

) : RecyclerView.Adapter<CatAdapter.CatViewHolder>(){

    inner class CatViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView)


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
        var tagsString : String = ""
        for(cat in currentCat.tags){
            tagsString += cat.toString() + "\n"
        }
        holder.itemView.findViewById<TextView>(R.id.tvTags).text = tagsString
        Picasso.get().load(currentCat.url).into(holder.itemView.findViewById<ImageView>(R.id.imgcat))

        holder.itemView.findViewById<CardView>(R.id.cvCatItem).setOnClickListener {
            println("CAT ${position} HAS BEEN CLICKED COMMANDER")
            val intent : Intent = Intent(context, CatDetailsActivity::class.java).apply {
                putExtra("CatUrl", currentCat.url)
                putExtra("CatTags", tagsString)
            }
            startActivity(context,intent, null)

        }


    }

    override fun getItemCount(): Int {
        return cats.size
    }

    fun addCat(cat: Cat) {
        cats.add(cat)
        notifyItemInserted(cats.size - 1)
    }
    fun setCatsList(catsList : MutableList<Cat>) {
        for(cat : Cat in catsList){
            addCat(cat)
        }
    }

    fun getCatList(): MutableList<Cat> {
        return cats
    }
}
package com.turtywurty.cat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CatDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_details)

        val catUrl : String? = intent.getStringExtra("CatUrl")
        val catTags : String? = intent.getStringExtra("CatTags")

        Picasso.get().load(catUrl).into(findViewById<ImageView>(R.id.imgcatdetail))

        val tags = findViewById<TextView>(R.id.tvTagsDetail).apply {
            text = catTags
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}
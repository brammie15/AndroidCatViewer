package com.turtywurty.cat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.squareup.picasso.Picasso

class CatDetailsActivity : AppCompatActivity() {
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_details)
        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var backbtn = findViewById<ImageButton>(R.id.backbtn);
        backbtn.setOnClickListener {
            finish()
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionsmenu, menu)
        return true
    }
}
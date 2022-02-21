package com.turtywurty.cat

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.random.Random

class CatDetailsActivity : AppCompatActivity() {
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
    val executorService: ExecutorService = Executors.newFixedThreadPool(4)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.download -> {
                doKoolShit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun doKoolShit(){
        val catimgview : ImageView = findViewById<ImageView>(R.id.imgcatdetail)
        val bitmap : Bitmap = catimgview.drawable.toBitmap(catimgview.width, catimgview.height, Bitmap.Config.HARDWARE)

        val path : File = Environment.getExternalStorageDirectory()
        val dir : File = File(path.toString() + "/DCIM")

        dir.mkdirs()
        val fileName : String = Random.nextInt(0, 10000).toString() + ".png"
        val file : File = File(dir, fileName)

        val out : OutputStream


        out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.flush()
        out.close()

    }
}

class AsyncGetImage() : AsyncTask{

}
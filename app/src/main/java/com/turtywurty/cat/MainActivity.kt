package com.turtywurty.cat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCat: Button = findViewById<Button>(R.id.btnCat)
        val imgCat: ImageView = findViewById<ImageView>(R.id.imgCat)

        btnCat.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url : String = "https://cataas.com/cat?json=true"
            var outJson : String = ""
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                   println(response)
                    outJson = response
                    var reader : JSONObject  = JSONObject(outJson)
                    println(reader.toString(1))
                    var catpicUrl : String  = "https://cataas.com${reader.getString("url")}"
                    Picasso.get().load(catpicUrl).into(imgCat);
                },
                Response.ErrorListener { return@ErrorListener })

            queue.add(stringRequest)



        }
    }


}
package com.turtywurty.cat

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var catAdapter: CatAdapter
    val url : String = "https://cataas.com/cat?json=true"
    var cats : MutableList<Cat> = mutableListOf<Cat>()


    fun JSONArray.toMuteableList(): MutableList<JSONObject> = MutableList(length(), this::getJSONObject)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btnCat: Button = findViewById<Button>(R.id.btnCat)
        val rvCatPics : RecyclerView = findViewById<RecyclerView>(R.id.rvCatPics)
        catAdapter = CatAdapter(this,mutableListOf())
        catAdapter.setCatsList(cats);
        rvCatPics.adapter = catAdapter



        rvCatPics.layoutManager = LinearLayoutManager(this)

        btnCat.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
//                println(response)
                    var reader : JSONObject  = JSONObject(response)
//                println(reader.toString(1))
                    var catpicUrl : String  = "https://cataas.com${reader.getString("url")}"
                    println(catpicUrl)
                    var tags = JSONArray(reader.getString("tags"))

                    var tagsList = mutableListOf<String>()
                    for (i in 0 until tags.length()) {
                        tagsList.add(tags[i].toString())
                    }
                    println(tagsList.toString())
                    val cat = Cat(catpicUrl, tagsList)
                    catAdapter.addCat(cat)

                },
                Response.ErrorListener { return@ErrorListener })
            queue.add(stringRequest)
        }
    }

}
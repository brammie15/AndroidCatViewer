package com.turtywurty.cat

import android.graphics.Bitmap
import android.widget.ImageView
import com.squareup.picasso.Picasso

data class Cat(
  var url: String = "",
  var tags: MutableList<String> = mutableListOf<String>()

)
package com.emin.kacan.listofcountries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadFromUrl(url : String?,proggesDrawable : CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(proggesDrawable)
        .error(com.emin.kacan.listofcountries.R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)


}

fun placeHolderProggesBar(context:Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view : ImageView,url : String?){

    view.downloadFromUrl(url, placeHolderProggesBar(view.context))

}
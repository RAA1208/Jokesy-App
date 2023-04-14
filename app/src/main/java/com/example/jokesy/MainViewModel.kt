package com.example.jokesy

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(@SuppressLint("StaticFieldLeak") private val context:Context): ViewModel() {

    private var jokelist: Array<Jokes> = emptyArray()
    private var index = 0

    init {
        jokelist = loadJokesFromAssets()
    }

    private fun loadJokesFromAssets(): Array<Jokes> {
        val inputStream = context.assets.open("jokes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Jokes>::class.java)


    }
    fun getjokes() = jokelist[index]
    fun nextjoke() = jokelist[++index]
    fun previousjoke() = jokelist[--index]

}
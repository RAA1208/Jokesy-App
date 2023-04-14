package com.example.jokesy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainviewmodel: MainViewModel
    private val jokesetup: TextView
        get() = findViewById(R.id.setup)
    private val jokepunchline: TextView
        get() = findViewById(R.id.punchline)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainviewmodel = ViewModelProvider(this, MainViewModelfactory(application)).get(MainViewModel::class.java)
        setjokes(mainviewmodel.getjokes())
    }

    private fun setjokes(jokes: Jokes) {
        jokesetup.text = jokes.setup
        jokepunchline.text = jokes.punchline
    }

    fun onnext(view: View) {
        setjokes(mainviewmodel.nextjoke())
    }
    fun onprevious(view: View) {
        setjokes(mainviewmodel.previousjoke())
    }

    fun shareButton(view: View) {
        val sharebtn = findViewById<com.google.android.material.floatingactionbutton.
        FloatingActionButton>(R.id.share)
        sharebtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Hey! Let's see if you can answer this funny question: " + mainviewmodel.getjokes().setup)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))

        }
    }
}
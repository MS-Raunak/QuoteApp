package com.ms.quoteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    var quoteList : Array<Quote> = emptyArray()
    var index = 0
    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json") // open the file
        val size : Int = inputStream.available()  // find size of file
        val buffer = ByteArray(size)   // reading size of ByteArray and store in buffer
        inputStream.read(buffer)  // read inputstream and store buffer inside it
        inputStream.close()  // close the file

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun previousQuote() = quoteList[--index]
}
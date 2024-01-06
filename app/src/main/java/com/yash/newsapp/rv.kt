package com.yash.newsapp

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class rv : AppCompatActivity(){
    lateinit var recyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        val ss = load()
        recyclerview = findViewById(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

    }

    fun load(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"

// Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                    val data = response.getJSONArray("articles")

                    val allData = ArrayList<Data>()
                    for(i in 0 until data.length()){
                        val store = data.getJSONObject(i)
                        val allD = Data(store.getString("author"),
                            store.getString("title"), store.getString("url"),
                            store.getString("urlToImage"))

                        allData.add(allD)
                    }

                val adapter = CustomAdapter(allData)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error in rv",Toast.LENGTH_LONG).show()
            }
        )

        queue.add(stringRequest)
    }

    data class Data(val author: String,val title: String,
                    val url: String,val urlToImage: String)
}
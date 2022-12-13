package com.example.marvelcomicsinformationfinder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit


private const val API_KEY = "bee9bfded63aad9fcda5bb1d9e5996c1"

class MarvelFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()

        client ["https://gateway.marvel.com/v1/public/comics?ts=1&apikey=bee9bfded63aad9fcda5bb1d9e5996c1&hash=252c3b50d185e9d75f971cad0ad99b09",
                params, object : JsonHttpResponseHandler(){
                    override fun onFailure(call: Call, e: IOException) {
                        super.onFailure(call, e)
                    }
                    override fun onResponse(call: Call, response: Response) {
                        super.onResponse(call, response)
                    }
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {

                        progressBar.hide()

                        val gson =Gson()
                        //old code
                        //val resultsJSON = json.jsonObject.get("data") as JSONArray
                        val data = json.jsonObject.get("data") as JSONObject
                        val resultsJSON = data.getJSONArray("results")

                        val arrayComic = object : TypeToken<List<Comics>>() {}.type
                        val models: List<Comics> = gson.fromJson(resultsJSON.toString(), arrayComic)
                        recyclerView.adapter = RecyclerAdapter(models, this@MarvelFragment)

                        Log.v("MarvelFragment", "response successful")
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ){
                        progressBar.hide()

                        Log.v("MarvelFragment", errorResponse)
                    }

                }]
    }
        override fun onItemClick(item: Comics) {
            Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
        }
}
package com.qoolqas.moviedb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qoolqas.moviedb.BuildConfig
import com.qoolqas.moviedb.connection.Client
import com.qoolqas.moviedb.model.PopularMovieItem
import com.qoolqas.moviedb.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewmodel : ViewModel() {
    private val popular = MutableLiveData<List<PopularMovieItem>>()
    val api: String = BuildConfig.API_KEY

    fun init(page: Int){
        loadPopular(page)
    }
    fun loadPopular(page: Int) {
        Client().getApi().getPopular(api, page)
            .enqueue(object : Callback<PopularMovieResponse> {
                override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                    Log.d("failure popular", t.message.toString())
                }

                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>
                ) {
                    if (response.isSuccessful){
                        val respons:PopularMovieResponse? = response.body()
                        popular.postValue(respons?.result)
                    }else{
                        Log.d("else" ,"Failure")
                    }

                }

            })
    }
    fun livePopular(): LiveData<List<PopularMovieItem>> {
           return popular
    }
}
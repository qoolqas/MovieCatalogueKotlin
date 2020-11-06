package com.qoolqas.moviedb.ui.home.search

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.qoolqas.moviedb.BuildConfig
import com.qoolqas.moviedb.connection.Client
import com.qoolqas.moviedb.model.discover.DiscoverMovieResponse
import com.qoolqas.moviedb.model.discover.DiscoverResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMovieViewModel : ViewModel() {
    private var discover = MutableLiveData<MutableList<DiscoverResultsItem>>(mutableListOf())
    private val api: String = BuildConfig.API_KEY

    fun init(page: Int, query : String,language : String) {
        loadPopular(page,query, language)
    }

    fun loadPopular(page: Int, query : String,language : String) {
        Client().getApi().getSearchMovie(api, page,query,language)
            .enqueue(object : Callback<DiscoverMovieResponse> {
                override fun onFailure(call: Call<DiscoverMovieResponse>, t: Throwable) {
                    Log.d("failure discover", t.message.toString())
                }

                override fun onResponse(
                    call: Call<DiscoverMovieResponse>,
                    response: Response<DiscoverMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val respons: DiscoverMovieResponse? = response.body()
                        discover.postValue(discover.value?.apply { addAll(respons?.results!!)})

                    } else {
                        Log.d("else", "Failure")
                    }

                }

            })
    }
    fun observerData(owner: LifecycleOwner,observer: Observer<MutableList<DiscoverResultsItem>>) = discover.observe(owner, observer)

}

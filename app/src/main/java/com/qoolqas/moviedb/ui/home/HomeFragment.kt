package com.qoolqas.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.qoolqas.moviedb.R
import com.qoolqas.moviedb.model.nowplaying.NowPlayingResultsItem
import com.qoolqas.moviedb.model.popular.PopularResultsItem
import com.qoolqas.moviedb.ui.home.adapter.NowPlayingAdapter
import com.qoolqas.moviedb.ui.home.adapter.PopularAdapter
import com.qoolqas.moviedb.ui.home.viewmodel.NowPlayingViewModel
import com.qoolqas.moviedb.ui.home.viewmodel.PopularViewModel
import com.qoolqas.moviedb.utils.CirclePagerIndicatorDecoration
import com.qoolqas.moviedb.utils.LinePagerIndicatorDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularPb.visibility = View.VISIBLE
        popularDivider.visibility = View.GONE
        getPopular()
        getNowPlaying()


    }
    fun getPopular(){
        popularViewModel = ViewModelProviders.of(this).get(PopularViewModel::class.java)
        popularViewModel.init(1)
        popularViewModel.livePopular().observe(viewLifecycleOwner, Observer { popular ->
            popularRv.apply {
                popularRv.setHasFixedSize(true)
                popularRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = PopularAdapter(popular)
            }
            popularPb.visibility = View.GONE
            popularDivider.visibility = View.VISIBLE

        })
    }
    fun getNowPlaying(){
        nowPlayingViewModel = ViewModelProviders.of(this).get(NowPlayingViewModel::class.java)
        nowPlayingViewModel.init(1)
        nowPlayingViewModel.liveNowPlaying().observe(viewLifecycleOwner, Observer { nowPlaying ->
            rvPager.apply {
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(rvPager)
                rvPager.onFlingListener = null
                rvPager.addItemDecoration(LinePagerIndicatorDecoration())
                rvPager.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = NowPlayingAdapter(nowPlaying)
            }
            popularPb.visibility = View.GONE
            popularDivider.visibility = View.VISIBLE

        })
    }

}

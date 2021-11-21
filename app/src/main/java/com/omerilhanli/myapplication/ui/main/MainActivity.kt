package com.omerilhanli.myapplication.ui.main

import android.graphics.Color
import androidx.activity.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.omerilhanli.myapplication.ui.main.adapter.PeopleAdapter
import com.omerilhanli.myapplication.R
import com.omerilhanli.myapplication.common.observe
import com.omerilhanli.myapplication.data.*
import com.omerilhanli.myapplication.data.datasource.FetchError
import com.omerilhanli.myapplication.data.datasource.FetchResponse
import com.omerilhanli.myapplication.databinding.ActivityMainBinding
import com.omerilhanli.myapplication.ui.base.BaseActivity
import com.omerilhanli.myapplication.ui.main.adapter.RecyclerSwipeEndListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener, RecyclerSwipeEndListener {

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var adapter: PeopleAdapter

    override fun init() {
        binding.vm = mainViewModel
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE)
        adapter = PeopleAdapter(swipeEndListener = this)
        binding.recyclerView.adapter = adapter
    }

    override fun observeOn() {
        observe(mainViewModel.peopleResponse, ::handleList)
    }

    private fun handleList(resource: Resource<*>) {

        when (resource.status) {

            Status.SUCCESS -> {
                (resource.data as FetchResponse)
                    .apply {
                        mainViewModel
                            .run {
                                if (people.isNotEmpty()) {
                                    adapter.update(people, swipedForRefresh)
                                    swipedForRefresh = false
                                }
                            }
                    }
            }

            Status.ERROR -> {
                (resource.data as FetchError)
                    .apply {
                        showToastUI(errorDescription)
                    }
            }
        }
    }

    override fun onRefresh() {
        mainViewModel.apply {
            swipedForRefresh = true
            getPeople()
        }
    }

    override fun onSwipeEnd() {
        mainViewModel.apply {
            getPeople()
        }
    }
}
package com.example.mymviapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymviapp.NewsApplication
import com.example.mymviapp.R
import com.example.mymviapp.ui.DataStateListener
import com.example.mymviapp.ui.main.state.MainStateEvent
import com.example.mymviapp.ui.main.state.MainViewState
import com.example.mymviapp.utils.DataState
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment:Fragment(){

    private val newsViewModel: MainViewModel by viewModels() {
        MainViewModel.MainViewModelFactory((activity?.application as NewsApplication).repository)
    }

    lateinit var dataStateHandler: DataStateListener
    lateinit var mainRecyclerAdapter: MainListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initRecyclerView()
        triggerGetNewsEvent()
    }

    private fun subscribeObservers(){
        newsViewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            handleDataState(dataState)
        })

        newsViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            handleViewState(viewState)
        })
    }

    fun handleDataState(dataState: DataState<MainViewState>){
        println("DEBUG: DataState: ${dataState}")
        // Handle Loading and Message
        dataStateHandler.onDataStateChange(dataState)

        // Handle Data<T>
        dataState.data?.let{ event ->
            event.getContentIfNotHandled()?.let{ viewState ->
                viewState.news?.let { news ->
                    newsViewModel.setNews(news)
                }
            }
        }
    }

    fun handleViewState(viewState: MainViewState){
        println("DEBUG: ViewState: ${viewState}")
        viewState.news?.let{ news ->
            // Set recyclerview data
            mainRecyclerAdapter.submitList(news)

        }
    }

    fun triggerGetNewsEvent(){
        newsViewModel.setStateEvent(MainStateEvent.GetNewsEvent())
    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            mainRecyclerAdapter = MainListAdapter()
            adapter = mainRecyclerAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            dataStateHandler = context as DataStateListener
        }catch(e: ClassCastException){
            println("$context must implement DataStateListener")
        }

    }
}
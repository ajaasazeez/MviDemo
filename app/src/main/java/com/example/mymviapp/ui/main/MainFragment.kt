package com.example.mymviapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymviapp.R
import com.example.mymviapp.ui.DataStateListener
import com.example.mymviapp.ui.main.state.MainStateEvent

class MainFragment:Fragment() {
    lateinit var viewModel: MainViewModel
    lateinit var dataStateHandler: DataStateListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
        triggerGetNewsEvent()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            println("DEBUG: DataState: ${dataState}")

            // handle Data<T>
            dataState.data?.let{ mainViewState ->
                mainViewState.news?.let{
                    // set BlogPosts data
                    viewModel.setNews(it)
                }
            }

            // Handle Progress bar?
            dataState.loading.let{
                println("DEBUG: LOADING: ${it}")
            }

            // Handle message?
            dataState.message?.let{
                println("DEBUG: MESSAGE: ${it}")
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.news?.let {
                // set News data to RecyclerView
                println("DEBUG: Setting news to RecyclerView: ${viewState.news}")
            }

        })
    }

    fun triggerGetNewsEvent(){
        viewModel.setStateEvent(MainStateEvent.GetNewsEvent())
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
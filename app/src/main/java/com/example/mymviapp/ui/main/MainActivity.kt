package com.example.mymviapp.ui.main


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.example.mymviapp.R
import com.example.mymviapp.ui.DataStateListener
import com.example.mymviapp.utils.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),DataStateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()
    }

    fun showMainFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,
                MainFragment(), "MainFragment")
            .commit()
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        dataState?.let{
            // Handle loading
            showProgressBar(dataState.loading)

            // Handle Message
            dataState.message?.let{ event ->
                event.getContentIfNotHandled()?.let {message->
                    showToast(message)
                }

            }
        }
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar(isVisible: Boolean){
        if(isVisible){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.INVISIBLE
        }
    }
}
package com.example.mymviapp.ui.main


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mymviapp.R
import com.example.mymviapp.ui.DataStateListener
import com.example.mymviapp.utils.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataStateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MainFragment(), "MainFragment"
            )
            .commit()
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            // Handle loading
            showProgressBar(dataState.loading)

        }
    }


    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.INVISIBLE

    }
}
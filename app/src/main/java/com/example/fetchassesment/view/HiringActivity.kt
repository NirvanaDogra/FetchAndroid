package com.example.fetchassesment.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchassesment.R
import com.example.fetchassesment.databinding.ActivityHiringBinding
import com.example.fetchassesment.viewmodel.HiringState
import com.example.fetchassesment.viewmodel.HiringViewModel
import commons.DialogUtil

class HiringActivity : AppCompatActivity() {
    companion object {
        const val API_ERROR = "API Error"
        const val API_ERROR_DESCRIPTION = "Error with the API"
    }

    private lateinit var adapter: HiringListAdapter
    lateinit var viewModel: HiringViewModel
    private lateinit var binding: ActivityHiringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        observerData()
    }

    private fun observerData() {
        viewModel.hiringLiveData.observe(this) {
            adapter.submitData(it)
        }

        viewModel.sateLiveData.observe(this) {
            handleScreenState(it)
        }
    }

    private fun handleScreenState(hiringState: HiringState) {
        when (hiringState) {
            HiringState.ERROR -> showErrorScreen()
            HiringState.LOADING -> showProgressScreen()
            HiringState.SUCCESS -> showSuccessScreen()
        }
    }

    private fun showSuccessScreen() {
        binding.progressbarHiring.visibility = View.GONE
    }

    private fun showProgressScreen() {
        binding.progressbarHiring.visibility = View.VISIBLE
    }

    private fun showErrorScreen() {
        binding.progressbarHiring.visibility = View.GONE
        DialogUtil().showAlertDialog(this, API_ERROR, API_ERROR_DESCRIPTION)
    }

    private fun initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hiring)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_hiring)
        adapter = HiringListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HiringActivity)
            adapter = this@HiringActivity.adapter
            setHasFixedSize(true)
        }
        viewModel = ViewModelProvider(this)[HiringViewModel::class.java]
        viewModel.getHiringData()
    }
}
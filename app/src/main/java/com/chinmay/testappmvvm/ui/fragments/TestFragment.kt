package com.chinmay.testappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chinmay.testapp.adapter.TestListAdapter
import com.chinmay.testappmvvm.databinding.TestFragmentLayoutBinding
import com.chinmay.testappmvvm.viewmodels.TestListViewModel
import kotlinx.android.synthetic.main.test_fragment_layout.*

class TestFragment: Fragment() {

    private lateinit var viewDataBinding: TestFragmentLayoutBinding
    private lateinit var adapter: TestListAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            viewDataBinding = TestFragmentLayoutBinding.inflate(inflater,container,false).apply{
            viewmodel = ViewModelProviders.of(this@TestFragment).get(TestListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.viewmodel?.fetchTestList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.testListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateTestList(it)
        })
    }


    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = TestListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }
    }
}
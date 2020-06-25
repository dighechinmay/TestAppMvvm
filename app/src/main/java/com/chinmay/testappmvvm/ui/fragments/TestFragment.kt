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
import com.chinmay.testappmvvm.model.Tests
import com.chinmay.testappmvvm.utils.Globals
import com.chinmay.testappmvvm.viewmodels.TestListViewModel
import kotlinx.android.synthetic.main.test_fragment_layout.*

class TestFragment: Fragment() {

    private lateinit var viewDataBinding: TestFragmentLayoutBinding
    private lateinit var adapter: TestListAdapter
    private  var listTag = ""



    companion object {
        const val TAG = "listname"


        fun newInstance(tag: String): TestFragment {
            val fragment = TestFragment()

            val bundle = Bundle().apply {
                putString(TAG, tag)
            }

            fragment.arguments = bundle

            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            listTag = arguments?.getString("listname")!!
            viewDataBinding = TestFragmentLayoutBinding.inflate(inflater,container,false).apply{
            viewmodel = ViewModelProviders.of(this@TestFragment).get(TestListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        if(Globals.firstTimeCall) {
            when (listTag) {
                "school" -> {
                viewDataBinding.viewmodel?.fetchTestList("school")
                viewDataBinding.viewmodel?.testListLive?.observe(viewLifecycleOwner, Observer {
                    adapter.updateTestList(it)
                    Globals.schoolTestList = it
                })

            }


                "class" -> {
                    viewDataBinding.viewmodel?.fetchTestList("class")
                    viewDataBinding.viewmodel?.testListLive?.observe(viewLifecycleOwner, Observer {
                        adapter.updateTestList(it)
                        Globals.classTestList = it
                        Globals.firstTimeCall = false
                    })
                }
            }
        }else {

            when (listTag) {
                "school" -> adapter.updateTestList(Globals.schoolTestList)
                "class" -> adapter.updateTestList(Globals.classTestList)
            }

        }
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
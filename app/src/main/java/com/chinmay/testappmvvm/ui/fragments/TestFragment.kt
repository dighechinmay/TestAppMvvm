package com.chinmay.testappmvvm.ui.fragments

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.testapp.adapter.TestListAdapter
import com.chinmay.testappmvvm.R
import com.chinmay.testappmvvm.databinding.TestFragmentLayoutBinding
import com.chinmay.testappmvvm.model.Tests
import com.chinmay.testappmvvm.utils.Globals
import com.chinmay.testappmvvm.viewmodels.TestListViewModel
import kotlinx.android.synthetic.main.test_fragment_layout.*

class TestFragment: Fragment() {

    private lateinit var viewDataBinding: TestFragmentLayoutBinding
    private lateinit var adapter: TestListAdapter
    private  var listTag = ""

    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }



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
                    setUpItemTouchHelper(it)
                })

            }


                "class" -> {
                    viewDataBinding.viewmodel?.fetchTestList("class")
                    viewDataBinding.viewmodel?.testListLive?.observe(viewLifecycleOwner, Observer {
                        adapter.updateTestList(it)
                        Globals.classTestList = it
                        Globals.firstTimeCall = false
                        setUpItemTouchHelper(it)
                    })
                }
            }
        }else {

            when (listTag) {
                "school" -> {
                    adapter.updateTestList(Globals.schoolTestList)
                    setUpItemTouchHelper(Globals.schoolTestList)
                }
                "class" -> {
                    adapter.updateTestList(Globals.classTestList)
                    setUpItemTouchHelper(Globals.classTestList)
                }
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


    fun setUpItemTouchHelper(list: ArrayList<Tests>){
        val mItemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                list.removeAt(position)
                adapter.notifyDataSetChanged()
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val deleteIcon = ContextCompat.getDrawable(context!!, R.drawable.delete)
                val intrinsicWidth = deleteIcon!!.intrinsicWidth
                val intrinsicHeight = deleteIcon!!.intrinsicHeight
                val background = ColorDrawable()
                val backgroundColor = Color.parseColor("#f44336")


                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val isCanceled = dX == 0f && !isCurrentlyActive

                if (isCanceled) {
                    clearCanvas(canvas, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                    super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    return
                }

                // Draw the red delete background
                background.color = backgroundColor
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                background.draw(canvas)

                // Calculate position of delete icon
                val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
                val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
                val deleteIconRight = itemView.right - deleteIconMargin
                val deleteIconBottom = deleteIconTop + intrinsicHeight

                // Draw the delete icon
                deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
                deleteIcon.draw(canvas)

                return super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val mItemTouchHelper = ItemTouchHelper(mItemTouchHelperCallback)
        mItemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }
}
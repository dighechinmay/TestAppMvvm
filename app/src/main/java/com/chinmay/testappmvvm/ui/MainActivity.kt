package com.chinmay.testappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.chinmay.testappmvvm.R
import com.chinmay.testappmvvm.ui.fragments.TestFragment
import com.chinmay.testappmvvm.utils.Globals
import com.chinmay.testappmvvm.utils.inTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        replaceFragment("school")

        setOnClickListeners()
    }

    private fun setOnClickListeners() {

        button_first.setOnClickListener{


            replaceFragment("school")

            button_first.setBackgroundResource(R.drawable.button_selected)
            schoollevel_textview.setTextColor(ContextCompat.getColor(this,R.color.selected))
            button_second.setBackgroundResource(R.drawable.button_not_selected)
            classlevel_textview.setTextColor(ContextCompat.getColor(this,R.color.deselected))

        }

        button_second.setOnClickListener {

            replaceFragment("class")

            button_first.setBackgroundResource(R.drawable.button_not_selected)
            schoollevel_textview.setTextColor(ContextCompat.getColor(this,R.color.deselected))
            button_second.setBackgroundResource(R.drawable.button_selected)
            classlevel_textview.setTextColor(ContextCompat.getColor(this,R.color.selected))
        }
    }

    private fun replaceFragment(tag: String){

        val fragment = TestFragment.newInstance(tag)
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, fragment) }
    }



}

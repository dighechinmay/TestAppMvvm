package com.chinmay.testappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chinmay.testappmvvm.R
import com.chinmay.testappmvvm.ui.fragments.TestFragment
import com.chinmay.testappmvvm.utils.inTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragment = TestFragment()
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container,fragment)
        }
    }


}

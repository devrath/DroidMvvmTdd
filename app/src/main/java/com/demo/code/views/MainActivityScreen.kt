package com.demo.code.views

import android.os.Bundle
import com.demo.code.R
import com.demo.code.base.BaseActivity

class MainActivityScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, PlayListFragment.newInstance())
                .commit()
    }
}
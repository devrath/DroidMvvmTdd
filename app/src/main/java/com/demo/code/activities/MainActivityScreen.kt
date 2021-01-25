package com.demo.code.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.code.fragments.PlayListFragment
import com.demo.code.R

class MainActivityScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, PlayListFragment.newInstance())
                .commit()
    }
}
package com.demo.code.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.code.fragments.playlist.PlayListFragment
import com.demo.code.R
import com.demo.code.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, PlayListFragment.newInstance())
                .commit()
    }
}
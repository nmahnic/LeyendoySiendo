package com.nicomahnic.dadm.leyendoysiendo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders.RvOrdersFragment

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RvOrdersFragment.newInstance())
                .commitNow()
        }
    }
}
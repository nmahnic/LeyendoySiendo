package com.nicomahnic.dadm.leyendoysiendo.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nicomahnic.dadm.leyendoysiendo.R


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val userName = intent.getStringExtra(Intent.EXTRA_TEXT)

        userName?.let {
            User.name = userName
        }

        Log.d("NM", "ARGS: ${userName}")

    }

    object User{
        var name = ""
    }
}
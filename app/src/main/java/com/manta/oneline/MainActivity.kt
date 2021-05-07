package com.manta.oneline

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DialogInterface.OnDismissListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ResolutionDialog().show(supportFragmentManager, "")
    }

    override fun onDismiss(dialog: DialogInterface?) {
        finish()
    }
}
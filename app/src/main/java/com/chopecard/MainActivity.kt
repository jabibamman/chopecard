package com.chopecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import injectModuleDependencies
import parseAndInjectConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parseAndInjectConfiguration()
        injectModuleDependencies(this)
    }
}

package com.chopecard.presentation.view

import android.app.Activity
import android.os.Bundle
import com.chopecard.BuildConfig
import com.chopecard.R
import injectModuleDependencies
import parseAndInjectConfiguration

class CollectorView : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parseAndInjectConfiguration()
        injectModuleDependencies(this)

        // get base url from build config
        val baseUrl = BuildConfig.BASE_URL
        println("BASE_URL: $baseUrl")
    }
}
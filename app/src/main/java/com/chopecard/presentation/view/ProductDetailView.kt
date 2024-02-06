package com.chopecard.presentation.view

import android.app.Activity
import android.os.Bundle
import com.chopecard.R
import injectModuleDependencies
import parseAndInjectConfiguration

class ProductDetailView : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ca va dire que la view affichera activity_main (tu va dans layout/activity_main)
        setContentView(R.layout.activity_main)
        parseAndInjectConfiguration()
        injectModuleDependencies(this)
        // Étape 1: Obtenir une référence au conteneur body_layout
        val bodyLayout: ViewGroup = findViewById(R.id.body_layout)

        // Étape 2: Utiliser LayoutInflater pour gonfler product_detail_layout.xml
        val productView = LayoutInflater.from(this).inflate(R.layout.product_detail_layout, bodyLayout, false)

        // Étape 3: Ajouter le layout gonflé à body_layout
        bodyLayout.addView(productView)
        // get base url from build config
        val baseUrl = BuildConfig.BASE_URL
        val externalUrl = BuildConfig.EXTERNAL_URL
        println("BASE_URL: $baseUrl")
        println("EXTERNAL_URL: $externalUrl")
    }
}

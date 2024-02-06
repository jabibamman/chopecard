package com.chopecard.presentation.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chopecard.BuildConfig
import com.chopecard.R
import injectModuleDependencies
import parseAndInjectConfiguration

class ProductDetailView : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Définir le contenu de la vue sur le layout approprié
        setContentView(R.layout.bodylayout)

        // Charger la configuration
        parseAndInjectConfiguration()

        // Injecter les dépendances du module
        injectModuleDependencies(this)

        // Étape 1: Obtenir une référence au conteneur body_layout
        val bodyLayout: ViewGroup = findViewById(R.id.body_layout)

        // Étape 2: Utiliser LayoutInflater pour gonfler product_detail_layout.xml
        val productView = LayoutInflater.from(this).inflate(R.layout.product_detail_layout, bodyLayout, false)

        // Étape 3: Ajouter le layout gonflé à body_layout
        bodyLayout.addView(productView)

        // Obtenir l'URL de base depuis BuildConfig
        val baseUrl = BuildConfig.BASE_URL
        val externalUrl = BuildConfig.EXTERNAL_URL
        println("BASE_URL: $baseUrl")
        println("EXTERNAL_URL: $externalUrl")
    }
}

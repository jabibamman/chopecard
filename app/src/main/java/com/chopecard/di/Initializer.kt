package com.chopecard.di

import android.content.Context
import com.chopecard.BuildConfig
import com.chopecard.di.modules.coreModule
import com.chopecard.di.modules.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.error.ApplicationAlreadyStartedException
import org.koin.dsl.module

fun injectModuleDependencies(context: Context) {
    try {

        startKoin {
            androidContext(context)
            modules(modules)
        }
    } catch (alreadyStart: ApplicationAlreadyStartedException) {
        loadKoinModules(modules)
    }
}

/**
 * Function to parse current build config depending on build variant (debug/release)
 * and inject the structure representing it in the dependency tree
 */
fun parseAndInjectConfiguration() {
    val apiConf = FakeJsonConf(baseUrl = BuildConfig.BASE_URL, externalUrl = BuildConfig.EXTERNAL_URL)

    modules.add(
        module {
            single { apiConf }
        }
    )
}


private val modules = mutableListOf(coreModule, remoteModule)

data class FakeJsonConf(val baseUrl: String, val externalUrl: String)

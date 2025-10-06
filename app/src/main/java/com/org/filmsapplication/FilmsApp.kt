package com.org.filmsapplication

import android.app.Application
import com.org.data.di.dataModule
import com.org.filmsapplication.core.di.appModule
import com.org.filmsapplication.core.di.domainModule
import com.org.filmsapplication.core.di.presentationModule
import org.koin.core.context.startKoin

class FilmsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
            modules(dataModule)
            modules(domainModule)
            modules(presentationModule)
        }
    }
}
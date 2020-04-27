package uk.cookpad.ukhospital

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uk.cookpad.ukhospital.di.dbModule
import uk.cookpad.ukhospital.di.httpModule
import uk.cookpad.ukhospital.di.repositoryModule
import uk.cookpad.ukhospital.di.viewModelModule

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                dbModule,
                httpModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
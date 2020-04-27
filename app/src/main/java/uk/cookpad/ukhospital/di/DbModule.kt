package uk.cookpad.ukhospital.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import uk.cookpad.ukhospital.data.db.AppDatabase

const val DB_NAME = "UkHospitalDb"
val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
    single { get<AppDatabase>().hospitalDao() }
}
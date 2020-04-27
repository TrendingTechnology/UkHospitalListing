package uk.cookpad.ukhospital.di

import org.koin.dsl.module
import retrofit2.Retrofit
import uk.cookpad.ukhospital.data.network.HospitalApi
import uk.cookpad.ukhospital.data.network.HttpClient

val httpModule = module {
    single { HttpClient.retrofit }
    single { get<Retrofit>().create(HospitalApi::class.java) }
}
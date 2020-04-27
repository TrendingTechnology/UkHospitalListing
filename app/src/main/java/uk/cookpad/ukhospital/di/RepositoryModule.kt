package uk.cookpad.ukhospital.di

import org.koin.dsl.module
import uk.cookpad.ukhospital.data.HospitalDetailRepository


val repositoryModule = module {
    single {
        HospitalDetailRepository(
            api = get(),
            hospitalDetailDao = get()
        )
    }
}
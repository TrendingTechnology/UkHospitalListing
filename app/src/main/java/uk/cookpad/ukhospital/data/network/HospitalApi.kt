package uk.cookpad.ukhospital.data.network

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming

interface HospitalApi {
    companion object {
        const val PATH_LOGS = "/data/foi/Hospital.csv"
    }

    @Streaming
    @GET(PATH_LOGS)
    fun getHospitalData(): Flowable<ResponseBody>
}
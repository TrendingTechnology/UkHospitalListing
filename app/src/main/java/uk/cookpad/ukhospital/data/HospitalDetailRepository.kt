package uk.cookpad.ukhospital.data

import androidx.paging.DataSource
import io.reactivex.Flowable
import uk.cookpad.ukhospital.data.db.HospitalDetailDao
import uk.cookpad.ukhospital.data.dto.HospitalDetail
import uk.cookpad.ukhospital.data.network.HospitalApi


class HospitalDetailRepository(
    private val hospitalDetailDao: HospitalDetailDao,
    private val api: HospitalApi
) {

    fun getHospitalData(): Flowable<Int> {
        return api.getHospitalData()
            .map { responseBody ->
                responseBody
                    .charStream()
                    .readLines()
                    .map { listItem -> listItem.split("�") }
            }
            .map { csvRowsList ->
                val csvRowsListMutable = csvRowsList.toMutableList()

                // Remove the CSV heading and assign it to headingRow
                val headingRow = csvRowsListMutable.first()
                csvRowsListMutable.removeAt(0)

                //Map rows of data and match it to DB Dao HospitalDetail
                val listOfHospitalList = csvRowsListMutable.map { csvRowsItem ->
                    val mapOfHospitalDetail = csvRowsItem.mapIndexed { index, value ->
                        Pair(first = headingRow.getOrNull(index).orEmpty(), second = value)
                    }.toMap()
                    HospitalDetail.fromMap(mapOfHospitalDetail)
                }

                //Insert processed entity to DB
                hospitalDetailDao.insertAll(listOfHospitalList)
            }.map { it.size }
    }

    fun getHospitalListPaging(sectorName: String? = null): DataSource.Factory<Int, HospitalDetail> {
        return if (sectorName == null) {
            hospitalDetailDao.getAllHospital()
        } else {
            hospitalDetailDao.getAllHospitalBySector(sectorName)
        }
    }
}
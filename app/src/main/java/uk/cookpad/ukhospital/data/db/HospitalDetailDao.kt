package uk.cookpad.ukhospital.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.cookpad.ukhospital.data.dto.HospitalDetail

@Dao
interface HospitalDetailDao {
    @Query("SELECT * FROM HospitalDetail where sector = :sectorName ORDER BY organisationName")
    fun getAllHospitalBySector(sectorName: String): DataSource.Factory<Int, HospitalDetail>

    @Query("SELECT * FROM HospitalDetail ORDER BY organisationName")
    fun getAllHospital(): DataSource.Factory<Int, HospitalDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hospital: List<HospitalDetail>): List<Long>
}
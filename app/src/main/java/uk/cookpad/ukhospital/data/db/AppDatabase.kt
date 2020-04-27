package uk.cookpad.ukhospital.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.cookpad.ukhospital.data.dto.HospitalDetail


@Database(entities = [HospitalDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hospitalDao(): HospitalDetailDao
}

package github.sachin2dehury.runapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.sachin2dehury.runapp.data.entities.Run

@Database(
    entities = [Run::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao
}
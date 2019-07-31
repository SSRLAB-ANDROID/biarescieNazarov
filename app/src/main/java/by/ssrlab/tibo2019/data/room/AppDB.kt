package by.krokam.biarescie.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.data.items.Section


@Database(
        entities = [Section::class, Exhibit::class],
        version = 1, exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract val appDao: AppDao

    companion object {
        var name = "bible_db_v2"
    }
}

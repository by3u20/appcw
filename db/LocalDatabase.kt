package moe.yhi.apps.playground.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    version = 1,
    entities = [
        Site::class,
        User::class,
        Delivery::class,
    ],
    exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun siteDao(): SiteDao
    abstract fun userDao(): UserDao
    abstract fun deliveryDao(): DeliveryDao

    companion object {
        private var instance: LocalDatabase? = null

        private fun buildDatabase(context: Context): LocalDatabase {
            return Room
                .databaseBuilder(context, LocalDatabase::class.java, "constructor.db")
                .createFromAsset("db/sample.db")
                .build()
        }

        fun getInstance(context: Context): LocalDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }
    }
}
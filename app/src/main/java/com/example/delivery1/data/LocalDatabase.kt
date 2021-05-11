package com.example.delivery1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    version = 3,
    entities = [
        Delivery::class,
        UserEntity::class,
   ],
    exportSchema = true
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        //val migration_1_2: Migration = object: Migration(1, 2) {
        //    override fun migrate(database: SupportSQLiteDatabase) {
        //        database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
        //    }
        //}

        fun getDatabase(context: Context): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "Local_Database"
                )
                    //.addMigrations(migration_1_2)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
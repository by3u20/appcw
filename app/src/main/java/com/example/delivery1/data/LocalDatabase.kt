package com.example.delivery1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        Delivery::class,
    ],
    exportSchema = true
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        private fun buildDatabase(context: Context): LocalDatabase {
            return Room
                .databaseBuilder(context, LocalDatabase::class.java, "constructor.db")
                .createFromAsset("db/sample.db")
                .build()
        }

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
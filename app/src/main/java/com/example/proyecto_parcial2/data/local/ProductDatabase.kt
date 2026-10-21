package com.example.proyecto_parcial2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class, PlaceholderEntity::class, CambioEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun placeholderDao(): PlaceholderDao
    abstract fun cambioDao(): CambioDao

    companion object {
        @Volatile
        private var instancia: ProductDatabase? = null

        fun obtener(context: Context): ProductDatabase =
            instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "app_database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instancia = it }
            }
    }
}

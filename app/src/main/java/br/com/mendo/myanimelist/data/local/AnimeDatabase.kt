package br.com.mendo.myanimelist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AnimeEntity::class], version = 1)
abstract class AnimeDatabase: RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE anime ADD COLUMN cachedAt INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}
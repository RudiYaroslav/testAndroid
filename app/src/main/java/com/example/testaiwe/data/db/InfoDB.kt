package com.example.testaiwe.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [InfoRoom::class, MarketCapPercentageRoom::class], version = 1)
abstract class InfoDB : RoomDatabase() {
    abstract fun InfoDao():InfoDao
}
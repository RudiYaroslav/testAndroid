package com.example.testaiwe.data.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testaiwe.model.MarketCapPercentage

@Entity(tableName = "infoData")
data class InfoRoom(

   @PrimaryKey(autoGenerate = true)
    val id:Long,

    @ColumnInfo(name = "active_cryptocurrencies")
    val activeCryptocurrencies:Int,

    @ColumnInfo(name = "upcoming_icos")
    val upcomingIcos :Int,

    @ColumnInfo(name = "ongoing_icos")
    val ongoingIcos : Int,

    @ColumnInfo(name = "ended_icos")
    val endedIcos : Int,

    @ColumnInfo(name = "markets")
    val markets : Int

)

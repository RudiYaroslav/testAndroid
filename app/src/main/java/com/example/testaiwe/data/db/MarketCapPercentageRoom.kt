package com.example.testaiwe.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.testaiwe.model.MarketCapPercentage

@Entity(
    tableName = "market_caps_percentage",
    foreignKeys = [
        ForeignKey(
            entity = InfoRoom::class,
            parentColumns = ["id"],
            childColumns = ["info_id"],
            onDelete = ForeignKey.CASCADE)]
    )
data class MarketCapPercentageRoom(

    @PrimaryKey(autoGenerate = true)
    val id : Long,

    @ColumnInfo(name = "name")
    val name :String,
    @ColumnInfo(name = "percentage")
    val percentage: Double,

    @ColumnInfo(name = "info_id")
    var  infoId : Long,

)
fun MarketCapPercentageRoom.toModel():MarketCapPercentage{
    return MarketCapPercentage(
        name = this.name,
        percentage = this.percentage
    )
}

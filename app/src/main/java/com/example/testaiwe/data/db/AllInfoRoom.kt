package com.example.testaiwe.data.db


import androidx.room.Embedded
import androidx.room.Relation
import com.example.testaiwe.model.Info

data class AllInfoRoom(
    @Embedded
    val infoRoom: InfoRoom,
    @Relation(
        parentColumn = "id",
        entityColumn = "info_id"
    )
    val marketCapsPercentageRoom: List<MarketCapPercentageRoom>
)
fun AllInfoRoom.toModel():Info{
    return  Info(
        activeCryptocurrencies = infoRoom.activeCryptocurrencies,
        upcomingIcos = infoRoom.upcomingIcos,
        ongoingIcos = infoRoom.ongoingIcos,
        endedIcos = infoRoom.endedIcos,
        markets = infoRoom.markets,
        marketCapsPercentage = marketCapsPercentageRoom.map {
            it.toModel()
        }
    )
}

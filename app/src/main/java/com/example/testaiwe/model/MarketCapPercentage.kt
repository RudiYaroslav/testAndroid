package com.example.testaiwe.model

import com.example.testaiwe.data.db.MarketCapPercentageRoom
import com.google.gson.annotations.SerializedName

data class MarketCapPercentage (

  val name: String,
  val percentage :Double
)
fun MarketCapPercentage.toRoom():MarketCapPercentageRoom{
  return MarketCapPercentageRoom(
    name = this.name,
    percentage = this.percentage,
    id = 0,
    infoId = 0
  )
}

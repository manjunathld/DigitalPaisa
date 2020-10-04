package com.ldbrossoftwares.digitalpaisa.prices.model

data class AssetsData (
   val symbol: String,
   val name: String,
   val priceUSD: Double,
   val changePercent24Hours: Double
) {}
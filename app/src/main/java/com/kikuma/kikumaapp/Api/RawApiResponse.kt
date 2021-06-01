package com.kikuma.kikumaapp.Api

import com.google.gson.annotations.SerializedName

data class RawApiResponse(

	@field:SerializedName("Acne Comedonal")
	val acneComedonal: Double,

	@field:SerializedName("Acne Vulgaris")
	val acneVulgaris: Double
)

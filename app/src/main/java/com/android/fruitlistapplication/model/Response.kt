package com.android.fruitlistapplication.model


import com.google.gson.annotations.SerializedName


data class Response(

	@field:SerializedName("fruit")
	val fruit: List<FruitItem?>
)
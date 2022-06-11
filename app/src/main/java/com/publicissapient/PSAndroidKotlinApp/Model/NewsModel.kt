package com.publicissapient.PSAndroidKotlinApp.Model

import com.google.gson.annotations.SerializedName

data class NewsModelClass (
    @SerializedName("category")var category:String?=null,
    @SerializedName("data")var data:List<News>,
        )

data class News(
    @SerializedName("author")var author:String?=null,
    @SerializedName("content")var content:String?=null,
    @SerializedName("date")var date:String?=null,
    @SerializedName("id")var id:String?=null,
    @SerializedName("imageUrl")var imageUrl:String?=null,
    @SerializedName("readMoreUrl")var readMoreUrl:String?=null,
    @SerializedName("time")var time:String?=null,
    @SerializedName("title")var title:String?=null,
    @SerializedName("url")var url:String?=null,
)
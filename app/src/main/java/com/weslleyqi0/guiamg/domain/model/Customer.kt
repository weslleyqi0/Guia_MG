package com.weslleyqi0.guiamg.domain.model

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    val id: String = "",
    var name: String = "",
    var description: String = "",
    var address: String = "",
    var categories: List<String> = arrayListOf(),
    var phone: String = "",
    @get:PropertyName("instagram_link")
    @set:PropertyName("instagram_link")
    var instagramLink: String = "",
    @get:PropertyName("facebook_link")
    @set:PropertyName("facebook_link")
    var facebookLink: String = "",
    @get:PropertyName("maps_link")
    @set:PropertyName("maps_link")
    var mapsLink: String = "",
    @get:PropertyName("main_image")
    @set:PropertyName("main_image")
    var mainImage: String = "",
    @get:PropertyName("added_at")
    @set:PropertyName("added_at")
    var addedAt: String = "",
    var highlighted: Boolean = false,
    var imagesList: ArrayList<String> = arrayListOf()
) : Parcelable


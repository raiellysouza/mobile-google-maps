package com.example.myplacesapp

import com.google.android.gms.maps.model.LatLng

data class MyPlace (
    val id: String,
    val name: String,
    val latLng: LatLng,
    val description: String = ""
)
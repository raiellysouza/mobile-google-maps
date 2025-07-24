package com.example.myplacesapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.android.gms.maps.model.LatLng

class MyPlacesViewModel : ViewModel() {

    val myPlaces: SnapshotStateList<MyPlace> = mutableStateListOf<MyPlace>().apply {

        add(MyPlace("1", "UFC Quixadá", LatLng(-4.979089750971326, -39.056514252078195), "Campus da Universidade Federal do Ceará em Quixadá"))
        add(MyPlace("2", "Praça José de Barros", LatLng(-4.9723, -39.0156), "Praça principal de Quixadá"))
        add(MyPlace("3", "Pedra da Galinha Choca", LatLng(-4.9902, -39.0507), "Famosa formação rochosa"))
    }

    val selectedPlaces: SnapshotStateList<MyPlace> = mutableStateListOf<MyPlace>()

    fun toggleSelection(place: MyPlace) {
        if (selectedPlaces.contains(place)) {
            selectedPlaces.remove(place)
        } else {
            if (selectedPlaces.size < 2) {
                selectedPlaces.add(place)
            } else {
                selectedPlaces.clear()
                selectedPlaces.add(place)
            }
        }
    }
}

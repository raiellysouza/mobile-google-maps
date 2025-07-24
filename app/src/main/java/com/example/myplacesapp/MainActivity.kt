package com.example.myplacesapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Permissão de localização concedida!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permissão de localização negada. O mapa pode não funcionar corretamente.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        setContent {
            MyPlacesAppTheme {
                MapsScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MapsScreen(modifier: Modifier = Modifier) {
    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    val context = LocalContext.current

    val UFC = LatLng(-4.979089750971326, -39.056514252078195)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(UFC, 12f)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            markerPosition = latLng
            Toast.makeText(context, "Marcador adicionado em: ${latLng.latitude}, ${latLng.longitude}", Toast.LENGTH_SHORT).show()
        }
    ) {
        Marker(
            state = MarkerState(position = UFC),
            title = "UFC",
            snippet = "Campus Quixadá"
        )

        markerPosition?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Novo Marcador",
                snippet = "Coordenadas: ${it.latitude}, ${it.longitude}"
            )
        }
    }
}
package com.example.myplacesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myplacesapp.ui.theme.MyPlacesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Solicitação de permissão para localização
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
// Permissão concedida
                }
            }
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOC
                        ATION)
            }
            setContent {
                MapsScreen()
            }
        }
    }

@Composable
fun MapsScreen() {
    val UFC = LatLng(-4.979089750971326, -39.056514252078195)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(UFC, 12f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = UFC),
            title = "UFC",
            snippet = "Campus Quixadá"
        )
    }
}
// Adiciona marcador ao clicar no mapa
markerPosition ?.let {
    Marker(
        state = MarkerState( position =
            it),
        title = "Novo Marcador" ,
        snippet = "Coordenadas:
        ${it.latitude}, ${it.longitude}"
    )
}
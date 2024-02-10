package com.projeto.terramap

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.core.app.ActivityCompat
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.projeto.terramap.databinding.ActivityLocalizacaoBinding
import com.google.android.gms.location.LocationServices
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import android.util.Log



class localizacaoActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLocalizacaoBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocalizacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Se a permissão ainda não foi concedida, solicitar ao usuário
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            // Se a permissão foi concedida, obter a localização
            obterLocalizacao()
        }
    }

    private fun obterLocalizacao() {
        // Verificar se a permissão de localização foi concedida
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Se a permissão não foi concedida, não podemos obter a localização
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Log.d("loccc", location.toString())
                    val toast = Toast.makeText(
                        this, "latitude:" +
                                location.latitude.toString() + " longitude:" +
                                location.longitude!!.toString(), Toast.LENGTH_LONG
                    )
                    toast.show()
                } else {
                    Toast.makeText(
                        this,
                        "Não foi possível obter a localização.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            // Verificar se a permissão foi concedida
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permissão concedida, obter a localização
                obterLocalizacao()
            } else {
                // Permissão negada, informar ao usuário
                Toast.makeText(
                    this,
                    "Permissão de localização negada.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}

package com.projeto.terramap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.util.Log
//import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityHomeBinding

class homeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun VisualizarUser(view: View) {
        IrparaTelaVisUsuario()
    }

    private fun IrparaTelaVisUsuario() {
        val VisualizarUserActivity = Intent(this, FirstFragment::class.java)
        startActivity(VisualizarUserActivity)
    }

    //fun buttonlocation(view: View) {
    //    Log.d("HomeActivity", "Botão de Localização clicado")
    //    IrparaTelaLocalizacao()
    //}
    //private fun IrparaTelaLocalizacao() {
    //    Log.d("HomeActivity", "Ir para Tela de Localizacao")
    //    val LocalizacaoActivity = Intent(this, localizacaoActivity::class.java)
    //    startActivity(LocalizacaoActivity)
    //}

    fun buttonCadProp(view: View) {
        IrparaTelaCadPropriedades()
    }
    private fun IrparaTelaCadPropriedades() {
        val PropedadesActivity = Intent(this, CadPropriedadeActivity::class.java)
        startActivity(PropedadesActivity)
    }

    fun buttonLogout(view: View) {
        IrparaTelaLogin()
    }

    private fun IrparaTelaLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }
}
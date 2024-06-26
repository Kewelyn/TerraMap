package com.projeto.terramap

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.projeto.terramap.databinding.ActivityHomeBinding

class homeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


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

    fun VisualizarUser(view: View) {
        VisualizarUsuários()
    }

    private fun VisualizarUsuários() {
        val VisualizarUsuáriosIntent = Intent(this, UserlistActivity::class.java)
        startActivity(VisualizarUsuáriosIntent)
    }

    fun butalterarpropri(view: View) {
        AlterarMeusDados()
    }

    private fun AlterarMeusDados() {
        val AlterarPropri = Intent(this, UpdateActivity::class.java)
        startActivity(AlterarPropri)
    }

    fun VisualizarPropri(view: View) {
        VisualizarPropriedades()
    }

    private fun VisualizarPropriedades() {
        val VisualizarPropriedadesIntent = Intent(this, PropriedadeListActivity::class.java)
        startActivity(VisualizarPropriedadesIntent)
    }

    fun DeletarPropriedades(view: View) {
        DeletarPropriedades()
    }

    private fun DeletarPropriedades() {
        val DeletarPropriedadesIntent = Intent(this, DeleteActivity::class.java)
        startActivity(DeletarPropriedadesIntent)
    }

}


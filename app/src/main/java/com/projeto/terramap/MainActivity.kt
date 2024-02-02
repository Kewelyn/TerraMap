package com.projeto.terramap

import android.content.Intent
import android.view.View
import android.os.Bundle
//import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.NavController
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.NavHostFragment
import android.view.Menu
import android.view.MenuItem
import com.projeto.terramap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize NavController after layout inflation
        //val navHostFragment =
        //    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        //navController = navHostFragment.navController

        //binding.nextLogin.setOnClickListener {
        //    IrparaTelaLogin()
        //}
    }
    fun Next(view: View) {
        IrparaTelaLogin()
    }

    private fun IrparaTelaLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

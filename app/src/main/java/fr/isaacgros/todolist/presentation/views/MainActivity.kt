package fr.isaacgros.todolist.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import fr.isaacgros.todolist.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.loginFragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    fun navigateToFragment() {
        // TODO : Set the function to navigate between fragments
    }
}
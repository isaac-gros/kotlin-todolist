package fr.isaacgros.todolist.presentation.views

import android.content.Intent
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

        // Set nav host to navigate between fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    // Go to todos activity
    fun navigateToTodosActivity() {
        // TODO: Call API and store response in Shared Preferences
        navController.navigate(R.id.loginFragment_to_todosFragment)
    }

    // Go to sign up fragment
    fun navigateToSignUpFragment() {
        navController.navigate(R.id.loginFragment_to_signUpFragment)
    }
}
package fr.isaacgros.todolist.presentation.views

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.models.User
import fr.isaacgros.todolist.network.ServiceBuilder
import fr.isaacgros.todolist.network.UserService
import fr.isaacgros.todolist.utils.Consts
import fr.isaacgros.todolist.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set nav host to navigate between fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // Check if shared pref keys are set
        sharedPref = getSharedPreferences(Consts.SHARED_PREF_KEY, Context.MODE_PRIVATE)

        // Store MainActivity
        val sharedPrefs = getSharedPreferences(Consts.SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val firstName = sharedPrefs?.getString(Consts.FIRST_NAME_KEY, "")
        val lastName = sharedPrefs?.getString(Consts.LAST_NAME_KEY, "")
        if (firstName != null && lastName != null) {
            if(firstName.isNotBlank() && lastName.isNotBlank()) {
                this.navigateToTodosActivity()
            }
        }
    }

    // Go to todos activity
    fun navigateToTodosActivity() {
        navController.navigate(R.id.loginFragment_to_todosFragment)
    }

    // Go to sign up fragment
    fun navigateToSignUpFragment() {
        navController.navigate(R.id.loginFragment_to_signUpFragment)
    }

    // Go to login fragment
    fun navigateToLoginFragment() {
        navController.navigate(R.id.todosFragment_to_loginFragment)
    }
}
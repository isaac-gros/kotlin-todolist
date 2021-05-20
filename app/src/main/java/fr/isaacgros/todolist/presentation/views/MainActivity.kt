package fr.isaacgros.todolist.presentation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.models.User
import fr.isaacgros.todolist.network.ServiceBuilder
import fr.isaacgros.todolist.network.UserService
import fr.isaacgros.todolist.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        navController.navigate(R.id.loginFragment_to_todosFragment)
    }

    // Go to sign up fragment
    fun navigateToSignUpFragment() {
        navController.navigate(R.id.loginFragment_to_signUpFragment)
    }

    // Call API to login user
    fun loginUser(email: String, password: String) {
        val activityRef = this
        val request = ServiceBuilder.buildService(UserService::class.java)
        val call = request.getUser(email, password)

        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    // TODO: Store name and first name in shared pref
                    activityRef.navigateToTodosActivity()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { Log.d("USER", it) }
                Utils.alert(activityRef, "Une erreur est survenue lors de la connexion.")
            }
        })
    }
}
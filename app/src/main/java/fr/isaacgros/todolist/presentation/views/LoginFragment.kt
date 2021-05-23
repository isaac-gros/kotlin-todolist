package fr.isaacgros.todolist.presentation.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.models.User
import fr.isaacgros.todolist.network.ServiceBuilder
import fr.isaacgros.todolist.network.UserService
import fr.isaacgros.todolist.utils.Consts
import fr.isaacgros.todolist.utils.Utils
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve MainActivity
        val mainActivity = (activity as MainActivity)

        // Perform login action
        fragLogin_LoginButton.setOnClickListener {

            // Retrieve text views values
            val emailValue = fragLogin_EmailView.text.toString()
            val passwordValue = fragLogin_PasswordView.text.toString()

            // Check fields validity
            if(emailValue.isNotBlank() && passwordValue.isNotBlank()) {

                if(!Utils.emailValid(emailValue)) {
                    // Wrong email format
                    Utils.alert(mainActivity, "L'adresse email est dans un format invalide.")

                } else if(!Utils.passwordValid(passwordValue)) {
                    // Wrong password format
                    Utils.alert(mainActivity, "Le mot de passe doit faire au moins 6 caractères.")

                } else {
                    // Navigate to TODOS
                    this.loginUser(emailValue, passwordValue)
                }
            } else {
                Utils.alert(mainActivity, "Vous devez remplir les deux champs textes.");
            }
        }

        // Switch to signup screen
        fragLogin_SignupButton.setOnClickListener {
            mainActivity.navigateToSignUpFragment()
        }
    }

    // Call API to login user
    private fun loginUser(email: String, password: String) {
        val activityRef = (activity as MainActivity)
        val request = ServiceBuilder.buildService(UserService::class.java)
        val call = request.getUser(email, password)

        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {

                        // Store user details in shared pref
                        val sharedPref = activityRef.getSharedPreferences(Consts.SHARED_PREF_KEY, Context.MODE_PRIVATE)
                        if(sharedPref != null) {
                            sharedPref.edit().putString(Consts.FIRST_NAME_KEY, user.prenom).apply()
                            sharedPref.edit().putString(Consts.LAST_NAME_KEY, user.nom).apply()
                            activityRef.navigateToTodosActivity()
                        }
                    } else {
                        Utils.alert(activityRef, "Le serveur n'a pas retourné de valeur correcte.")
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { Log.d("USER", it) }
                Utils.alert(activityRef, "Une erreur est survenue lors de la connexion.")
            }
        })
    }
}
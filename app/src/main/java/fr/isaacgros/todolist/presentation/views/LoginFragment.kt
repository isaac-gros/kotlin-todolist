package fr.isaacgros.todolist.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.utils.Utils
import kotlinx.android.synthetic.main.fragment_login.*

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
            val formValid = false
            if(emailValue.isNotBlank() && passwordValue.isNotBlank()) {

                if(!Utils.emailValid(emailValue)) {
                    // Wrong email format
                    Utils.alert(mainActivity, "L'adresse email est dans un format invalide.")

                } else if(!Utils.passwordValid(passwordValue)) {
                    // Wrong password format
                    Utils.alert(mainActivity, "Le mot de passe doit faire au moins 6 caractères.")

                } else {
                    // Navigate to TODOS
                    (activity as MainActivity).loginUser(emailValue, passwordValue)
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
}
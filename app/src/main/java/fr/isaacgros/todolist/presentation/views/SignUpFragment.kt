package fr.isaacgros.todolist.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.utils.Utils
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Store MainActivity
        val mainActivity = (activity as MainActivity)

        // Perform sign up action
        fragSignUp_SignUpButton.setOnClickListener{

            // Retrieve form values
            val firstNameValue = fragSignUp_firstNameView.text.toString()
            val lastNameValue = fragSignUp_lastNameView.text.toString()
            val emailValue = fragSignUp_emailView.text.toString()
            val passwordValue = fragSignUp_passwordView.text.toString()

            // Check if form doesn't contain any blank field
            val signUpFormValues = mutableListOf<String>(firstNameValue, lastNameValue, emailValue, passwordValue)
            if(Utils.formCompleted(signUpFormValues)) {

                if(!Utils.emailValid(emailValue)) {
                    // Wrong email format
                    Utils.alert(mainActivity, "Vous devez saisir une adresse mail valide.")

                } else if (!Utils.passwordValid(passwordValue)) {
                    // Wrong password format
                    Utils.alert(mainActivity, "Le mot de passe doit contenir au moins 6 caractères.")
                } else {

                    // TODO : Store values in shared preferences
                    (activity as MainActivity).navigateToTodosActivity()
                }
            } else {
                Utils.alert(mainActivity, "Vous devez remplir tous les champs.")
            }
        }
    }
}
package fr.isaacgros.todolist.presentation.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.utils.Consts
import kotlinx.android.synthetic.main.fragment_todos.*

class TodosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve MainActivity
        val mainActivity = (activity as MainActivity)
        val sharedPref = this.activity?.getSharedPreferences(Consts.SHARED_PREF_KEY, Context.MODE_PRIVATE)

        // Reset shared prefs
        fragTodos_resetSharedPrefs.setOnClickListener {
            sharedPref?.edit()?.remove(Consts.FIRST_NAME_KEY)?.commit()
            sharedPref?.edit()?.remove(Consts.LAST_NAME_KEY)?.commit()
            mainActivity.navigateToLoginFragment()
        }

        // Set welcome message
        if(sharedPref != null) {
            val firstNameKey = sharedPref.getString(Consts.FIRST_NAME_KEY, "")
            val lastNameKey = sharedPref.getString(Consts.LAST_NAME_KEY, "")
            if (firstNameKey != null && lastNameKey != null) {
                val welcome = resources.getString(R.string.todos_welcome)
                fragTodos_welcomeText.text = String.format((welcome), firstNameKey, lastNameKey)
            }
        }
    }
}
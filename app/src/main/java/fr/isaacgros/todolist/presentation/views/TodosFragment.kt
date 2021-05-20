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

class TodosFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences

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
        sharedPref = mainActivity.getSharedPreferences(Consts.SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val firstNameKey = sharedPref.getString(Consts.FIRST_NAME_KEY, "")
        val lastNameKey = sharedPref.getString(Consts.LAST_NAME_KEY, "")

        // TODO: Set welcome message

    }
}
package fr.isaacgros.todolist.presentation.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.models.Task
import fr.isaacgros.todolist.presentation.data.TaskViewModel
import fr.isaacgros.todolist.utils.Consts
import fr.isaacgros.todolist.utils.Utils
import kotlinx.android.synthetic.main.fragment_todos.*

class TodosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TaskViewModel
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

        activity?.let {
            sharedPref = it.getSharedPreferences(Consts.SHARED_PREF_KEY, Context.MODE_PRIVATE)
        }

        context?.let {
            viewModel = TaskViewModel(it)
        }

        // Set welcome message
        val firstNameKey = sharedPref.getString(Consts.FIRST_NAME_KEY, "")
        val lastNameKey = sharedPref.getString(Consts.LAST_NAME_KEY, "")
        if (firstNameKey != null && lastNameKey != null) {
            val welcome = resources.getString(R.string.todos_welcome)
            fragTodos_welcomeText.text = String.format((welcome), firstNameKey, lastNameKey)
        }

        // Set list
        recyclerView = fragTodos_todosRecyclerView
        viewModel.displayAllTasks(fragTodos_todosRecyclerView, context)

        // Reset shared prefs
        fragTodos_resetSharedPrefs.setOnClickListener {
            resetSharedPrefs()
        }

        fragTodos_newTaskButton.setOnClickListener {
            createTask()
        }
    }

    private fun resetSharedPrefs() {
        sharedPref.edit()?.remove(Consts.FIRST_NAME_KEY)?.commit()
        sharedPref.edit()?.remove(Consts.LAST_NAME_KEY)?.commit()
        (activity as MainActivity).navigateToLoginFragment()
    }

    private fun createTask() {
        activity?.runOnUiThread {
            val taskContent = fragTodos_newTaskInput.text.toString()
            val newTask = Task(
                content = taskContent,
                done = false
            )
            viewModel.insertOneTask(newTask, recyclerView, context)
            Utils.alert((activity as MainActivity), "La tâche ''$taskContent'' a été crée")
            fragTodos_newTaskInput.text = null
        }
    }
}
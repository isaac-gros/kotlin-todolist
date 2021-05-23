package fr.isaacgros.todolist.presentation.data

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.animation.AnticipateInterpolator
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import fr.isaacgros.todolist.db.Database
import fr.isaacgros.todolist.models.Task
import fr.isaacgros.todolist.presentation.AdapterRecyclerView
import fr.isaacgros.todolist.utils.Consts
import kotlinx.android.synthetic.main.fragment_todos.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskViewModel(context: Context) : ViewModel() {

    private val db = Room.databaseBuilder(
        context,
        Database::class.java, Consts.DB_NAME
    ).build()

    // Je n'ai pas réussi à mettre à jour la liste une fois qu'une tâche est créée
    fun insertOneTask(task: Task, recyclerView: RecyclerView, context: Context?) {
        GlobalScope.launch() {
            val taskDao = db.tasksDao()
            taskDao.insertOne(task)
        }

    }

    // Je pense qu'il y a sûrement une meilleure façon pour ça... c'est ma propre solution !
    fun displayAllTasks(recyclerView: RecyclerView, context: Context?) {
        GlobalScope.launch {
            val tasks = db.tasksDao().getAll()

            // Reset RecyclerView
            recyclerView.adapter = AdapterRecyclerView(ArrayList(tasks), context)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }


}
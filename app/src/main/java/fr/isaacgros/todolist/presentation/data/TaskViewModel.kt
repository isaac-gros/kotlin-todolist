package fr.isaacgros.todolist.presentation.data

import android.content.Context
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskViewModel(context: Context) : ViewModel() {

    private val db = Room.databaseBuilder(
        context,
        Database::class.java, Consts.DB_NAME
    ).build()

    fun insertOneTask(task: Task) {
        GlobalScope.launch {
            val taskDao = db.tasksDao()
            taskDao.insertOne(task)
            Log.d("task", task.toString())
        }

    }

    // Je pense qu'il y a sûrement une meilleure façon pour ça... c'est ma propre solution !
    fun displayAllTasks(recyclerView: RecyclerView, context: Context?) {
        GlobalScope.launch {
            val tasks = db.tasksDao().getAll()
            recyclerView.adapter =
                AdapterRecyclerView(ArrayList(tasks), context)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }


}
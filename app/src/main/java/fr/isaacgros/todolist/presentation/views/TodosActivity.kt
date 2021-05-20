package fr.isaacgros.todolist.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isaacgros.todolist.R

class TodosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)
    }
}
package fr.isaacgros.todolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.isaacgros.todolist.models.Task

@Database(entities = [Task::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
}
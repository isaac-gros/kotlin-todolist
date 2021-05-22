package fr.isaacgros.todolist.db

import androidx.room.*
import fr.isaacgros.todolist.models.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Insert
    fun insertOne(task: Task)

    @Delete
    fun delete(image: Task)
}
package fr.isaacgros.todolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var content: String,
    var done: Boolean
) {
    override fun toString(): String {
        var doneText = if (done) "TERMINÉ" else "EN COURS"
        return "$content : $doneText"
    }
}
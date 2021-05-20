package fr.isaacgros.todolist.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object Utils {

    fun emailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    fun passwordValid(password: String): Boolean {
        return password.length > 6
    }

    fun alert(context: AppCompatActivity, message: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, message, duration)
        toast.show()
    }
}
package fr.isaacgros.todolist.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isaacgros.todolist.R
import fr.isaacgros.todolist.models.Task
import kotlinx.android.synthetic.main.item_todo.view.*

class AdapterRecyclerView (
    private var list: ArrayList<Task>,
    val context: Context?
) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            ) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val concat = ArrayList<Task>()
        concat.addAll(list)
        val item = concat[position]
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task, position: Int) {
            itemView.itemTodos_checkBox.text = task.content
            itemView.itemTodos_checkBox.isChecked = task.done
        }
    }
}
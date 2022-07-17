package com.example.recyclerviewstarter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private val userList: ArrayList<User> = arrayListOf(
        User(1, "xxx"),
        User(1, "xx1"),
        User(1, "xx2")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return UserHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.name.text = userList[position].name
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onViewAttachedToWindow(holder: UserHolder) {
        super.onViewAttachedToWindow(holder)
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView

        init {
            name = itemView.findViewById(R.id.name)
        }
    }
}
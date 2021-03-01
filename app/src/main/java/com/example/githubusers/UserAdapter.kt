package com.example.githubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mUsers = arrayListOf<String>()

    fun setUsers(users: List<String>) {
        mUsers.clear()
        mUsers.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentUser = mUsers[position]
        holder.bind(currentUser)

    }

    override fun getItemCount() = mUsers.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTvUsername: TextView = itemView.findViewById(R.id.tv_username)

        fun bind(currentUser: String) {
            mTvUsername.text = currentUser
        }

    }

}
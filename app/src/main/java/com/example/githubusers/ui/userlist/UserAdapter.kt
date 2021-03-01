package com.example.githubusers.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.data.UserResponse

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mUsers = arrayListOf<UserResponse>()

    fun setUsers(users: List<UserResponse>) {
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

        fun bind(currentUser: UserResponse) {
            mTvUsername.text = currentUser.login
        }

    }

}
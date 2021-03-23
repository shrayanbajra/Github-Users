package com.example.githubusers.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.UserResponse
import de.hdodenhof.circleimageview.CircleImageView

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

        private val mIvAvatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)
        private val mTvUsername: TextView = itemView.findViewById(R.id.tv_username)

        fun bind(currentUser: UserResponse) {
            currentUser.avatarUrl?.let { setIconFromUrl(it) }
            mTvUsername.text = currentUser.login
        }

        private fun setIconFromUrl(imageUrl: String?) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_user_place_holder_outlined)
                .into(mIvAvatar)
        }

    }

}
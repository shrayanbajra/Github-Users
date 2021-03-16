package com.example.githubusers.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.UserCacheEntity
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(
    private val mUserClickListener: UserClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mUsers = arrayListOf<UserCacheEntity>()

    fun setUsers(users: List<UserCacheEntity>) {
        mUsers.clear()
        mUsers.addAll(users)
        notifyDataSetChanged()
    }

    fun clear() {
        mUsers.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view, mUserClickListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentUser = mUsers[position]
        holder.bind(currentUser)

    }

    override fun getItemCount() = mUsers.size

    inner class UserViewHolder(itemView: View, private val clickListener: UserClickListener)

        : RecyclerView.ViewHolder(itemView) {

        private val mIvAvatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)
        private val mTvUsername: TextView = itemView.findViewById(R.id.tv_username)

        fun bind(currentUser: UserCacheEntity) {
            currentUser.avatarUrl?.let { setIconFromUrl(it) }
            mTvUsername.text = currentUser.username

            itemView.setOnClickListener { clickListener.onClicked(currentUser) }
        }

        private fun setIconFromUrl(imageUrl: String?) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_user_place_holder_outlined)
                .into(mIvAvatar)
        }

    }

    interface UserClickListener {

        fun onClicked(user: UserCacheEntity)

    }

}
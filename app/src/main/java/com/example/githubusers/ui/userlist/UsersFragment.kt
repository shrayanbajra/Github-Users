package com.example.githubusers.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.databinding.FragmentUserListBinding
import com.example.githubusers.util.ResultWrapper

class UsersFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private val mUserAdapter by lazy { UserAdapter() }

    private val mViewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRvUsers()

        getUsers()

    }

    private fun initRvUsers() {
        mBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = mUserAdapter
        }
    }

    private fun getUsers() {
        mViewModel.getUsers().observe(viewLifecycleOwner) { result ->

            when (result) {

                is ResultWrapper.Success -> {
                    val users = result.data
                    mUserAdapter.setUsers(users)
                    showRvUsers()
                }

                is ResultWrapper.Error -> {
                    val message = result.msg
                    showEmptyState(message = message)
                }

            }

        }
    }

    private fun showProgressBar() {
        mBinding.apply {
            progressBar.isVisible = true

            rvUsers.isVisible = false
            tvEmptyState.isVisible = false
        }
    }

    private fun showEmptyState(message: String) {
        mBinding.apply {
            tvEmptyState.isVisible = true
            tvEmptyState.text = message

            progressBar.isVisible = false
            rvUsers.isVisible = false
        }
    }

    private fun showRvUsers() {
        mBinding.apply {
            rvUsers.isVisible = true

            progressBar.isVisible = false
            tvEmptyState.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
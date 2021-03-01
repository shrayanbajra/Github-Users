package com.example.githubusers.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.data.Result
import com.example.githubusers.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

class UserListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private val mUserAdapter by lazy { UserAdapter() }

    private val mViewModel by lazy {
        ViewModelProvider(this)[UserListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRvUsers()

        getUsers(view)

    }

    private fun initRvUsers() {
        mBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            adapter = mUserAdapter
        }
    }

    private fun getUsers(view: View) {
        mViewModel.getUsers().observe(viewLifecycleOwner) { result ->

            when (result) {

                is Result.Success -> {
                    val users = result.data
                    mUserAdapter.setUsers(users)
                }

                is Result.Error -> {
                    val message = result.exception.message ?: "Couldn't get users"
                    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
                }

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
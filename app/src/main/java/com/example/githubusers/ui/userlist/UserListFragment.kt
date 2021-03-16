package com.example.githubusers.ui.userlist

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.data.UserCacheEntity
import com.example.githubusers.databinding.FragmentUserListBinding
import com.example.githubusers.util.ResultWrapper

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private val mUserAdapter by lazy { getUserAdapter() }

    private fun getUserAdapter() = UserAdapter(object : UserAdapter.UserClickListener {

        override fun onClicked(user: UserCacheEntity) {

            val username = user.username
            navigateToProfileFragment(username)

        }

        private fun navigateToProfileFragment(username: String?) {
            val action = UserListFragmentDirections
                .actionUserListFragmentToProfileFragment(username = username)
            findNavController().navigate(action)
        }

    })

    private val mViewModel by lazy {
        ViewModelProvider(this)[UserListViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_delete_all) {
            proceedToDeletingAllUsers()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun proceedToDeletingAllUsers() {
        mViewModel.deleteAllUsers()
        mUserAdapter.clear()
        val message = "No Users Found"
        showEmptyState(message)
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
                    mUserAdapter.clear()
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
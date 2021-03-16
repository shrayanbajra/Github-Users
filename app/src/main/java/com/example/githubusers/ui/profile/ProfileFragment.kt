package com.example.githubusers.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.ProfileResponse
import com.example.githubusers.databinding.FragmentProfileBinding
import com.example.githubusers.util.ResultWrapper
import com.example.githubusers.util.extensions.shortSnackBar
import com.example.githubusers.util.extensions.shortToast
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val mViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = getUsernameFromArguments()
        if (username.isNullOrBlank()) {
            shortToast(message = "Something went wrong")
        } else
            fetchUserProfileDetails(username)

    }

    private fun getUsernameFromArguments(): String? {
        val args = arguments?.let { ProfileFragmentArgs.fromBundle(it) }
        return args?.username
    }

    private fun fetchUserProfileDetails(username: String) {
        mViewModel.getUserProfileDetails(username = username).observe(viewLifecycleOwner) {

            when (it) {

                is ResultWrapper.Error -> shortSnackBar(message = it.msg)

                is ResultWrapper.Success -> updateViews(profileDetails = it.data)

            }

        }
    }

    private fun updateViews(profileDetails: ProfileResponse) {

        mBinding.apply {

            tvUsernameBody.text = profileDetails.username
            setIconUsingUrl(profileDetails.avatarUrl, ivAvatar)

        }

    }

    private fun setIconUsingUrl(imageUrl: String?, ivAvatar: CircleImageView) {
        Glide.with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.ic_user_place_holder_outlined)
            .into(ivAvatar)
    }

}
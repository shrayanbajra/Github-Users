package com.example.githubusers.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubusers.R
import com.example.githubusers.util.extensions.getNotAvailableIfNullOrBlank
import com.example.githubusers.util.extensions.shortToast

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { ProfileFragmentArgs.fromBundle(it) }
        val username = args?.username.getNotAvailableIfNullOrBlank()
        shortToast(message = username)

    }

}
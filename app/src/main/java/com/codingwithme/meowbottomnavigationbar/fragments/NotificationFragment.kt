package com.codingwithme.meowbottomnavigationbar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingwithme.meowbottomnavigationbar.R

class NotificationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            NotificationFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
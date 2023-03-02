package com.codingwithme.meowbottomnavigationbar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithme.meowbottomnavigationbar.R
import com.codingwithme.meowbottomnavigationbar.adapters.NotificationAdapter

class NotificationFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<NotificationAdapter.MyViewHolder>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        layoutManager = LinearLayoutManager(activity)
        var notiRecyclerView = view.findViewById<RecyclerView>(R.id.rcv_noti)
        notiRecyclerView.layoutManager = layoutManager
        adapter = NotificationAdapter()
        notiRecyclerView.adapter = adapter

        return view
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            NotificationFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
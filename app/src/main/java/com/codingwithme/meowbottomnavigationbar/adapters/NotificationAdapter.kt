package com.codingwithme.meowbottomnavigationbar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingwithme.meowbottomnavigationbar.R
import org.w3c.dom.Text

class NotificationAdapter() :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {

    private var titles = arrayOf(
    "Có lỗi xảy ra",
    "Có lỗi xảy ra",
    "Có lỗi xảy ra",
    "Có lỗi xảy ra"
    )

    private var descriptions = arrayOf(
    "Phát hiện sự cố đóng cắt của tụ bù. Kiểm tra ngay!",
    "Phát hiện sự cố đóng cắt của tụ bù. Kiểm tra ngay!",
    "Phát hiện sự cố đóng cắt của tụ bù. Kiểm tra ngay!",
    "Phát hiện sự cố đóng cắt của tụ bù. Kiểm tra ngay!"
    )

    private var dates = arrayOf(
    "now",
    "15 mins",
    "25 mins",
    "25 mins"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = titles[position]
        holder.description.text = descriptions[position]
        holder.date.text = dates[position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var description : TextView
        var date : TextView

        init {
            title = itemView.findViewById(R.id.txtTitle)
            description = itemView.findViewById(R.id.txtDescription)
            date = itemView.findViewById(R.id.txtDate)
        }
    }

}
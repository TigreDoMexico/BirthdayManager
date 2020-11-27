package com.david.birthdaymanager.activity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.david.birthdaymanager.R
import com.david.birthdaymanager.activity.adapters.BirthdayAdapter.*
import com.david.birthdaymanager.data.Birthday

class BirthdayAdapter : ListAdapter<Birthday, BirthdayViewHolder>(BirthdayComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayViewHolder {
        return BirthdayViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BirthdayViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.id)
    }

    class BirthdayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var birthdayItemView: TextView = itemView.findViewById(R.id.textView)
        var birthdayId: TextView = itemView.findViewById(R.id.data_id)

        fun bind(name: String?, id: Int?) {
            birthdayItemView.text = name
            birthdayId.text = id.toString()
        }

        companion object {
            fun create(parent: ViewGroup): BirthdayViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycleview_item, parent, false)
                return BirthdayViewHolder(view)
            }
        }
    }

    class BirthdayComparator : DiffUtil.ItemCallback<Birthday>() {
        override fun areItemsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
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
import kotlinx.android.synthetic.main.recycleview_item.view.*

class BirthdayAdapter : ListAdapter<Birthday, BirthdayViewHolder>(BirthdayComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayViewHolder {
        return BirthdayViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BirthdayViewHolder, position: Int) {
        val current = getItem(position)

        if(current.id != null) holder.bind(current.name, current.date, current.id)
    }

    class BirthdayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var onItemClick: ((Int) -> Unit)? = null

        private var birthdayItemView: TextView = itemView.birthday_card_name
        private var birthdayDateView: TextView = itemView.birthday_card_date

        fun bind(name: String?, date: String?, id: Int) {
            birthdayItemView.text = name
            birthdayDateView.text = date

            itemView.setOnClickListener{
                onItemClick?.invoke(id)
            }
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
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
            return oldItem.name == newItem.name || oldItem.date == newItem.date
        }
    }
}
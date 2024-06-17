package com.lexleontiev.tapyou.test.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lexleontiev.tapyou.test.app.R
import com.lexleontiev.tapyou.test.app.data.Point


class PointAdapter : ListAdapter<Point, PointAdapter.PointViewHolder>(PointDiffCallback()) {

    class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewX:TextView = itemView.findViewById(R.id.textViewX)
        private val textViewY: TextView = itemView.findViewById(R.id.textViewY)

        fun bind(point: Point) {
            textViewX.text = point.x.toString()
            textViewY.text = point.y.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_point, parent, false)
        return PointViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PointDiffCallback : DiffUtil.ItemCallback<Point>() {
    override fun areItemsTheSame(oldItem: Point, newItem: Point): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Point, newItem: Point): Boolean {
        return oldItem.x == newItem.x && oldItem.y == newItem.y
    }
}
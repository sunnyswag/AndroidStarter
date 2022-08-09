package com.example.touchconfliction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DemoRecyclerAdapter: RecyclerView.Adapter<DemoRecyclerAdapter.DemoRecyclerViewHolder>() {

    private val mData = listOf("1", "2", "3")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoRecyclerViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return DemoRecyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: DemoRecyclerViewHolder, position: Int) {
        holder.textView.text = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class DemoRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
            textView = itemView.findViewById(R.id.item_text)
        }
    }
}
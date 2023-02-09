package com.example.viewpager2starter


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/**
 * CreateTime: 2020/1/15 15:06
 * Author: hxd
 * Content:
 * UpdateTime:
 * UpdateName;
 * UpdateContent:
 */
class HorizontalVpAdapter internal constructor(private val mContext: Context) :
    RecyclerView.Adapter<HorizontalVpAdapter.HorizontalVpViewHolder>() {

    private var backgrounds = mutableListOf("#EDA179", "#F7BF7E", "#E0C17E", "#FAE589", "#F0EA86")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalVpViewHolder {
        return HorizontalVpViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_viewholder, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HorizontalVpViewHolder, position: Int) {
        holder.mTextView.text = "第 $position 个界面"
        holder.mTextView.setBackgroundColor(Color.parseColor(backgrounds[position]))
    }

    override fun getItemCount(): Int {
        return backgrounds.size
    }

    class HorizontalVpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView

        init {
            mTextView = itemView.findViewById(R.id.tv_pager_item)
        }
    }
}

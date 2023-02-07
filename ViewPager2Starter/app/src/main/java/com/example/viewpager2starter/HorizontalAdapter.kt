package com.example.viewpager2starter


import android.annotation.SuppressLint
import android.content.Context
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

    private var backgrounds = mutableListOf(R.color.black, R.color.purple_500, R.color.teal_200,
        R.color.purple_200, R.color.teal_700)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalVpViewHolder {
        return HorizontalVpViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_viewholder, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HorizontalVpViewHolder, position: Int) {
        holder.mTextView.text = "第 " + (position + 1) + " 个界面"
        holder.mTextView.setBackgroundResource(backgrounds[position])
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

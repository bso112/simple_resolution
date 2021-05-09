package com.manta.oneline

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manta.oneline.data.Memo
import com.manta.oneline.databinding.ResoultionTextBinding


open class ResolutionAdapter : ListAdapter<Memo, ResolutionAdapter.ViewHolder>(diffUtil) {

    interface OnClickItemListener {
        fun onClick()
        fun onLongClik()
    }

    private var onClickItemListener: OnClickItemListener? = null

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.content == newItem.content
            }
        }
    }


    class ViewHolder(val binding: ResoultionTextBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ResoultionTextBinding.inflate(LayoutInflater.from(parent.context)).apply {
            root.setOnClickListener { onClickItemListener?.onClick() }
            root.setOnLongClickListener {
                onClickItemListener?.onLongClik()
                true
            }
            //viewPager2의 item은 math_parent로 해야한다.
            //xml에서 match_parent로 해뒀지만 왜인지 wrap_content로 되서 명시적으로 지정해준다.
            tvResolution.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvResolution.text = getItem(position).content
    }


    fun setOnItemClick(onItemClickListener: OnClickItemListener) {
        this.onClickItemListener = onItemClickListener
    }

}
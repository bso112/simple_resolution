package com.manta.oneline

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manta.oneline.data.Memo
import com.manta.oneline.databinding.ResoultionTextBinding


class ResolutionAdapter : ListAdapter<Memo, ResolutionAdapter.ViewHolder>(diffUtil), ItemClickable{

    private var onItemClick : (()->Unit)? = null

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.content == newItem.content
            }
        }
    }


    class ViewHolder(val binding : ResoultionTextBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ResoultionTextBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener { onItemClick?.let { it() } }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvResolution.text = getItem(position).content
    }

    override fun setOnItemClick(onItemClick: () -> Unit) {
        this.onItemClick = onItemClick
    }


}
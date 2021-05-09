package com.manta.oneline.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


@BindingAdapter("adapter", "horizontalSwipe", "items", requireAll = false)
fun RecyclerView.setAdapter(
    listAdpater: ListAdapter<*, *>?,
    horizontalSwipe: Boolean?,
    items: List<Nothing>?
) {
    this.adapter = listAdpater
    listAdpater?.submitList(items)
    if (horizontalSwipe != null && horizontalSwipe) {
        this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    } else {
        this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}

@BindingAdapter("itemAnimated")
fun RecyclerView.isItemAnimated(
    itemAnimated : Boolean
){
    if(!itemAnimated)
        itemAnimator = null
}


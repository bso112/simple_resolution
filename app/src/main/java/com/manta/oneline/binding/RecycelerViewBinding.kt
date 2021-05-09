package com.manta.oneline.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manta.oneline.ItemClickable

@BindingAdapter("adapter", "horizontalSwipe", "items", "onItemClick", requireAll = false)
fun RecyclerView.setAdapter(listAdpater: ListAdapter<*, *>?, horizontalSwipe: Boolean?, items : List<Nothing>?, onItemClick : ()->Unit) {
    this.adapter = listAdpater
    listAdpater?.submitList(items)
    val itemClickable = listAdpater as? ItemClickable
    itemClickable?.setOnItemClick(onItemClick)
    if (horizontalSwipe != null && horizontalSwipe) {
        this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    } else {
        this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

}

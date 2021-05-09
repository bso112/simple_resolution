package com.manta.oneline.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.viewpager2.widget.ViewPager2


@BindingAdapter("adapter",  "items")
fun ViewPager2.setAdapter(
    listAdpater: ListAdapter<*, *>?,
    items: List<Nothing>?
) {
    this.adapter = listAdpater
    listAdpater?.submitList(items)
}
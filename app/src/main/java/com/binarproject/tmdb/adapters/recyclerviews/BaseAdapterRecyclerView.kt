package com.binarproject.tmdb.adapters.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.binarproject.tmdb.viewholders.ViewHolderAll

abstract class BaseAdapterRecyclerView<T> : RecyclerView.Adapter<ViewHolderAll<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAll<T> {
        val inflater =LayoutInflater.from(parent.context)
        val layout = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return ViewHolderAll(layout)
    }

    override fun onBindViewHolder(holder: ViewHolderAll<T>, position: Int) {
        val obj = getObjectForPosition(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjectForPosition(position: Int) : T

    protected abstract fun getLayoutIdForPosition(position: Int) : Int
}
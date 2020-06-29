package com.binarproject.tmdb.viewholders

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.binarproject.tmdb.BR

class ViewHolderAll<T>(val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : T) {
        binding.setVariable(BR.model, item)
        binding.executePendingBindings()
    }
}

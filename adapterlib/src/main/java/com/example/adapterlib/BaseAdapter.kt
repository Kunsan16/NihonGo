package com.example.adapterlib

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseAdapter<T> : PagedListAdapter<Bean, BaseViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Bean>() {
            override fun areItemsTheSame(oldItem: Bean, newItem: Bean): Boolean =
                    oldItem.status == newItem.status
            override fun areContentsTheSame(oldItem: Bean, newItem: Bean): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(itemLayoutId,parent,false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        val entity:Bean? = getItem(position)
        if (entity != null){
            bindView(entity,holder)
        }
    }

    protected abstract val itemLayoutId: Int

    protected abstract fun bindView(itemBean:Bean,holder: BaseViewHolder)

}
package com.gauravgoyal.test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gauravgoyal.test.R
import com.gauravgoyal.test.databinding.ItemOwesBinding


class DashBoardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<String> = ArrayList<String>()


    fun setData(list: ArrayList<String>) {
        items.clear()
        items.addAll(list)
        notifyItemRangeInserted(0, items.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemRequestBinding = DataBindingUtil
            .inflate<ItemOwesBinding>(
                LayoutInflater.from(parent.context), R.layout.item_owes,
                parent, false
            )


        return RequestViewHolder(itemRequestBinding)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RequestViewHolder).binding.value = items!![position]
    }


    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class RequestViewHolder(val binding: ItemOwesBinding) : RecyclerView.ViewHolder(binding.root)
}

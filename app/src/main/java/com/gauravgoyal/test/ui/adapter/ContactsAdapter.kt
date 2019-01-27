package com.gauravgoyal.test.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gauravgoyal.test.R
import com.gauravgoyal.test.databinding.ItemContactBinding
import com.gauravgoyal.test.model.ExpenseHolder

class ContactsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<ExpenseHolder> = ArrayList()


    fun setData(expenses: ArrayList<ExpenseHolder>) {

        if (this.items.size==0) {
            items.addAll(expenses)
            notifyItemRangeInserted(0, items.size)
        } else {
            val start = items.size-1
            items.addAll(expenses)
            notifyItemRangeInserted(start, expenses.size)
        }

        notifyDataSetChanged()


//        if (this.items == null) {
//            this.items = items
//            notifyItemRangeInserted(0, items.size)
//        } else {
//            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
//                override fun getOldListSize(): Int {
//                    return this@ContactsAdapter.items!!.size
//                }
//
//                override fun getNewListSize(): Int {
//                    return items.size
//                }
//
//                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//                    return this@ContactsAdapter.items!![oldItemPosition] === this@ContactsAdapter.items!![newItemPosition]
//                }
//
//                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//                    return this@ContactsAdapter.items!![oldItemPosition] == this@ContactsAdapter.items!![newItemPosition]
//                }
//
//            })
//            this.items = items
//            result.dispatchUpdatesTo(this)
//        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemRequestBinding = DataBindingUtil
            .inflate<ItemContactBinding>(
                LayoutInflater.from(parent.context), R.layout.item_contact,
                parent, false
            )

//        onclickCallBack?.let {
//            itemRequestBinding.setCallback(onclickCallBack)
//        }

        return RequestViewHolder(itemRequestBinding)

    }


    fun getExpenseData() = items

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RequestViewHolder).binding.holder = items!![position]
    }


    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class RequestViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)
}

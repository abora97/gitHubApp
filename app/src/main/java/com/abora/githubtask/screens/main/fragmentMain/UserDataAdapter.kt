package com.abora.githubtask.screens.main.fragmentMain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abora.githubtask.data.models.UserRepositoriesData
import com.abora.githubtask.databinding.ItemUsersBinding

class UserDataAdapter constructor(
    val actions: UserAction
) : RecyclerView.Adapter<UserDataAdapter.MyHolder>() {

    var list: MutableList<UserRepositoriesData> = arrayListOf()
    lateinit var context: Context

    fun setDate(list: List<UserRepositoriesData>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    fun updateDate(list: List<UserRepositoriesData>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.sliderRowBinding.data =  list[position]
        holder.itemView.setOnClickListener {
            actions.onUserClick(list[position],holder.itemView)
        }

    }

    inner class MyHolder(val sliderRowBinding: ItemUsersBinding) :
        RecyclerView.ViewHolder(sliderRowBinding.root)

    interface UserAction {
        fun onUserClick(item: UserRepositoriesData, view: View)
    }

}
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
) : RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        val viewHolder =  ItemUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(viewHolder){
            actions.onUserClick(list[it],parent)
        }
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(
        itemListView: ItemUsersBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemListView.root) {
        private var binding: ItemUsersBinding? = null

        init {
            binding=itemListView
            itemListView.root.setOnClickListener {
                // this will be called only once.
                onItemClicked(adapterPosition)
            }
        }

        fun bind(data: UserRepositoriesData) {
            //bind data with the component
            binding?.data=data
        }
    }

    interface UserAction {
        fun onUserClick(item: UserRepositoriesData, view: View)
    }

}
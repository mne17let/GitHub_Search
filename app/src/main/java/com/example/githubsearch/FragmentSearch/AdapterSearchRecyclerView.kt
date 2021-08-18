package com.example.githubsearch.FragmentSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.Model.User
import com.example.githubsearch.R

class AdapterSearchRecyclerView: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemsList: List<Any> = emptyList()

    fun setList(list: List<Any>){
        itemsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewForHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_search, parent, false)

        return SearchUserViewHolder(viewForHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as SearchUserViewHolder
        holder.bind(itemsList[position] as User)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}
package com.example.githubsearch.FragmentSearch

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.Model.User
import com.example.githubsearch.R

class SearchUserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private lateinit var textView: TextView

    fun bind(user: User){
        textView = itemView.findViewById(R.id.id_textview_search_recyclerview_item)
        textView.text = user.login
    }
}
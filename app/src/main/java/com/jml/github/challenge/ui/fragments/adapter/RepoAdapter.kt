package com.jml.github.challenge.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jml.github.challenge.R
import com.jml.github.challenge.ui.models.RepositoryUI

class RepoAdapter(private val onItemClicked: (idRepo: Long, sharedView: View) -> Unit) :
    ListAdapter<RepositoryUI, RepoAdapter.ViewHolder>(RepoDiffCallback()) {

    class ViewHolder(private val view: View, val onItemClicked: (idRepo: Long, sharedView: View) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.titleTextView)
        private val tvDescription: TextView = view.findViewById(R.id.descriptionTextView)
        private val tvStars: TextView = view.findViewById(R.id.starsTextView)

        fun bind(item: RepositoryUI) {
            view.tag = item.id
            view.setOnClickListener {
                val idRepo = it.tag as Long
                onItemClicked(idRepo, tvName)
            }

            ViewCompat.setTransitionName(tvName, item.id.toString())

            tvName.text = item.name
            tvDescription.text = item.description
            tvStars.text = item.stars.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repos, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun isEmpty() : Boolean = itemCount == 0
}

class RepoDiffCallback : DiffUtil.ItemCallback<RepositoryUI>() {
    override fun areItemsTheSame(oldItem: RepositoryUI, newItem: RepositoryUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryUI, newItem: RepositoryUI): Boolean {
        return oldItem == newItem
    }
}
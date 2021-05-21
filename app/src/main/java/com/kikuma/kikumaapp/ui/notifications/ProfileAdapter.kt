package com.kikuma.kikumaapp.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.databinding.ItemsHistoryBinding

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private var listHistory = ArrayList<HistoryEntity>()

    fun setHistory(history: List<HistoryEntity>?) {
        if (history == null) return
        this.listHistory.clear()
        this.listHistory.addAll(history)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemsHistoryBinding = ItemsHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(itemsHistoryBinding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val history = listHistory[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int = listHistory.size

    class ProfileViewHolder(private val binding: ItemsHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            with(binding) {
                tvDiseaseName.text = history.disease
                tvPost.text = history.posted
            }
        }
    }

}
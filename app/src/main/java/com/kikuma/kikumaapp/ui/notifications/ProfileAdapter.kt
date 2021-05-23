package com.kikuma.kikumaapp.ui.notifications

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.databinding.ItemsHistoryBinding
import com.kikuma.kikumaapp.ui.detailarticle.DetailArticleInfoActivity
import com.kikuma.kikumaapp.ui.result.DiseaseResultActivity

class ProfileAdapter : PagedListAdapter<HistoryEntity, ProfileAdapter.ProfileViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryEntity>() {
            override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
                return oldItem.historyId == newItem.historyId
            }
            override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemsHistoryBinding = ItemsHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(itemsHistoryBinding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val history = getItem(position)
        if (history != null) {
            holder.bind(history)
        }
    }

    class ProfileViewHolder(private val binding: ItemsHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            with(binding) {
                tvDiseaseName.text = history.disease
                tvPost.text = history.posted

                cvItemTips.setOnClickListener {
                    val intent = Intent(itemView.context, DiseaseResultActivity::class.java)
                    intent.putExtra(DiseaseResultActivity.EXTRA_HISTORY_ID, history.historyId)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

}
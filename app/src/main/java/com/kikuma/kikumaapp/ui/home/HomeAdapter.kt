package com.kikuma.kikumaapp.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.databinding.ItemsArticleBinding
import com.kikuma.kikumaapp.ui.detailarticle.DetailArticleInfoActivity

class HomeAdapter : PagedListAdapter<ArticleEntity, HomeAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
                return oldItem.articleId == newItem.articleId
            }
            override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemsArticleBinding = ItemsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemsArticleBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    class HomeViewHolder(private val binding: ItemsArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity) {
            with(binding) {
                tvTitle.text = article.title
                tvDesc.text = article.description

                btnViewMore.setOnClickListener {
                    val intent = Intent(itemView.context, DetailArticleInfoActivity::class.java)
                    intent.putExtra(DetailArticleInfoActivity.EXTRA_ARTICLE, article.articleId)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }
}
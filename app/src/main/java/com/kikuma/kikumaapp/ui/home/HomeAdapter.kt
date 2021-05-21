package com.kikuma.kikumaapp.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.databinding.ItemsArticleBinding
import com.kikuma.kikumaapp.ui.detailarticle.DetailArticleInfoActivity

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var listArticles = ArrayList<ArticleEntity>()

    fun setArticles(article: List<ArticleEntity>?) {
        if (article == null) return
        this.listArticles.clear()
        this.listArticles.addAll(article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemsArticleBinding = ItemsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemsArticleBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = listArticles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = listArticles.size


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
/*
                Glide.with(this)
                    .load(article.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

 */
            }
        }
    }
}
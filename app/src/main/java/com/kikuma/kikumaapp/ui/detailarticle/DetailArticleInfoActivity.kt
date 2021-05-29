package com.kikuma.kikumaapp.ui.detailarticle

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.databinding.ActivityDetailArticleInfoBinding
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import com.kikuma.kikumaapp.vo.Status

class DetailArticleInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }

    private lateinit var detailArticleInfoBinding: ActivityDetailArticleInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailArticleInfoBinding = ActivityDetailArticleInfoBinding.inflate(layoutInflater)
        setContentView(detailArticleInfoBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailArticleViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val articleId = extras.getString(EXTRA_ARTICLE)
            if (articleId != null) {
                viewModel.setSelectedArticle(articleId)

                viewModel.detailArticle.observe(this, { detailArticle ->
                    if (detailArticle != null) {
                        when (detailArticle.status) {
                            Status.LOADING -> detailArticleInfoBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (detailArticle.data != null) {
                                detailArticleInfoBinding.progressBar.visibility = View.GONE
                                populateArticle(detailArticle.data)
                            }
                            Status.ERROR -> {
                                detailArticleInfoBinding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun populateArticle(articleEntity: ArticleEntity) {
        detailArticleInfoBinding.tvTitle.text = articleEntity.title
        detailArticleInfoBinding.tvDescription.text = articleEntity.description
        detailArticleInfoBinding.tvPost.text = resources.getString(R.string.post, articleEntity.posted)

        Glide.with(this)
            .load(articleEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
            .into(detailArticleInfoBinding.imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
package com.kikuma.kikumaapp.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kikuma.kikumaapp.data.source.local.entity.TipsEntity
import com.kikuma.kikumaapp.databinding.ItemsTipsListBinding

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    private var listTips = ArrayList<TipsEntity>()

    fun setTips(tips: List<TipsEntity>?) {
        if (tips == null) return
        this.listTips.clear()
        this.listTips.addAll(tips)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemsTipsListBinding = ItemsTipsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(itemsTipsListBinding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val tips = listTips[position]
        holder.bind(tips)
    }

    override fun getItemCount(): Int {
        return listTips.size
    }

    inner class ResultViewHolder(private val binding: ItemsTipsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tips: TipsEntity) {
            binding.tvTips.text = tips.tips
        }
    }
}
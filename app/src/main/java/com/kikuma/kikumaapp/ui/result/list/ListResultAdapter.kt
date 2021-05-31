package com.kikuma.kikumaapp.ui.result.list

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.ModelResultEntity
import com.kikuma.kikumaapp.databinding.ItemsListResultBinding
import com.kikuma.kikumaapp.ui.result.DiseaseResultFragment


class ListResultAdapter : RecyclerView.Adapter<ListResultAdapter.ListResultViewHolder>() {

    private var listResult = ArrayList<ModelResultEntity>()

    fun setResult(result: List<ModelResultEntity>?) {
        Log.d("ini resuladapter", result.toString())
        if (result == null) return
        this.listResult.clear()
        this.listResult.addAll(result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListResultViewHolder {
        val itemsListResultBinding = ItemsListResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListResultViewHolder(itemsListResultBinding)
    }

    override fun onBindViewHolder(holder: ListResultViewHolder, position: Int) {
        val result = listResult[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return listResult.size
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListResultViewHolder(private val binding: ItemsListResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(results: ModelResultEntity) {
            with(binding) {
                Log.d("inidia", results.historyParent)
                tvDiseaseName.text = results.disease
//                tvPercentage.text = results.percentage

                cvItemDisease.setOnClickListener {
                    onItemClickCallback?.onItemClicked(results) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(result: ModelResultEntity)
    }
}
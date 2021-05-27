package com.kikuma.kikumaapp.ui.hospital

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.HospitalEntity
import com.kikuma.kikumaapp.databinding.ItemsHospitalBinding

class NearestHospitalAdapter : RecyclerView.Adapter<NearestHospitalAdapter.HospitalViewHolder>() {

    private var listHospital = ArrayList<HospitalEntity>()

    fun setHospitals(hospital: List<HospitalEntity>?) {
        if (hospital != null) {
            this.listHospital.clear()
            this.listHospital.addAll(hospital)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val itemsHospitalBinding = ItemsHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(itemsHospitalBinding)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospital = listHospital[position]
        holder.bind(hospital)
    }

    override fun getItemCount(): Int {
        return listHospital.size
    }

    class HospitalViewHolder(private val binding: ItemsHospitalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hospital: HospitalEntity) {
            with(binding) {
                tvHospital.text = hospital.hospital
                tvAddress.text = hospital.address
                tvRating.text = hospital.rate.toString()

                Glide.with(itemView)
                    .load(hospital.imagePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(binding.imagePoster)
            }
        }
    }
}
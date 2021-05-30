package com.kikuma.kikumaapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HospitalResponse(
    var hospitalId: String,
    var hospital: String,
    var address: String,
    var rate: Double,
    var price: String,
    var imagePath: String
): Parcelable

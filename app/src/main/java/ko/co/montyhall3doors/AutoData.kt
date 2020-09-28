package ko.co.montyhall3doors

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AutoData(
    val count: String,
    val success: String,
    val percentage: String,
    val drawable: Int
) : Parcelable
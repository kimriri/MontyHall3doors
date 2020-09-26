package ko.co.montyhall3doors

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

@Parcelize
data class AutoData(val count: String, val success: String, val percentage: String,val SuccessOrNot:Boolean) : Parcelable
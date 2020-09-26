package ko.co.montyhall3doors

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_auto.view.*

class AutoDataViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var view: View = v

    fun bind(item: AutoData) {

        view.item_tv_count.text = item.count
        view.item_tv_success.text = item.success
        view.item_tv_percentage.text = item.percentage
    }
}
package ko.co.montyhall3doors

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_auto.view.*

class AutoAdapter : RecyclerView.Adapter<AutoDataViewHolder>() {

    private val itemList = mutableListOf<AutoData>()

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoDataViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_auto, parent, false)
        return AutoDataViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: AutoDataViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            when (item.SuccessOrNot) {
                true -> view.item_auto.setBackgroundColor(Color.parseColor("#D262FA5D"))
            }
            bind(item)

        }
    }

    fun addItem(autoDataList: List<AutoData>) {
        itemList.apply {
            clear()
            addAll(autoDataList)

        }
        notifyDataSetChanged()
    }
}
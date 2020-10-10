package ko.co.montyhall3doors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AutoAdapter(private val context: Context) : RecyclerView.Adapter<AutoDataViewHolder>() {
    private val itemList = mutableListOf<AutoData>()
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoDataViewHolder {
        val inflatedView =
            LayoutInflater.from(parent. context).inflate(R.layout.item_auto, parent, false)
        return AutoDataViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: AutoDataViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item, context)
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
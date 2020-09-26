package ko.co.montyhall3doors

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auto_play.*

class ListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_play)

        val autoDataList: ArrayList<AutoData> = intent.getParcelableArrayListExtra("AutoDataList")
        val adapter = AutoAdapter()
        AutoPlayRecyCleview.adapter = adapter
        adapter.addItem(autoDataList)
    }
}
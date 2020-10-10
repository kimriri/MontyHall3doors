package ko.co.montyhall3doors

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auto_play.*
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_play)

        val autoDataList: ArrayList<AutoData> = intent.getParcelableArrayListExtra("AutoDataList")
        val adapter = AutoAdapter(this)
        AutoPlayRecyCleview.adapter = adapter
        adapter.addItem(autoDataList)

        AutoPlayButtonEventView()

    }
    private fun AutoPlayButtonEventView() {

        btn_auto_play_restart.setOnClickListener {
            finish()
        }
    }

}
package ko.co.montyhall3doors

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rnds = (0..2).random()
        print("$rnds" + "입니다")
        Log.e("for 문의 결과는", "$rnds" + "입니다")

        if (rnds == 0) {
            img_door1_back.setImageResource(R.drawable.supercar_64)
            img_door1_back.visibility = View.GONE

        } else if (rnds == 1) {
            img_door2_back.setImageResource(R.drawable.supercar_64)
            img_door2_back.visibility = View.GONE
        } else {
            img_door3_back.setImageResource(R.drawable.supercar_64)
            img_door3_back.visibility = View.GONE


        }
    }
}

package ko.co.montyhall3doors

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    var sum: Long = 0
    var cont: Long = 0
    var rnds_string = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_event = BtnEvent();
        btn_next.setOnClickListener(btn_event);
        btn_restart.setOnClickListener(btn_event);
        btn_view_results.setOnClickListener(btn_event)
        rnds()

    }

    fun rnds() {
        var first_rnds = (0..2).random()

        rnds_string = first_rnds
        Start_rnds()
        Select_doors()
    }

    fun Start_rnds() {

        when (rnds_string) {

            0 -> {
                img_door1_back.setImageResource(R.drawable.supercar_64)
                img_door2_back.setImageResource(R.drawable.goat_64)
                img_door3_back.setImageResource(R.drawable.goat_64)
                img_door1_back.visibility = View.VISIBLE

            }
            1 -> {
                img_door2_back.setImageResource(R.drawable.supercar_64)
                img_door1_back.setImageResource(R.drawable.goat_64)
                img_door3_back.setImageResource(R.drawable.goat_64)
                img_door2_back.visibility = View.VISIBLE
            }
            else -> {
                img_door3_back.setImageResource(R.drawable.supercar_64)
                img_door2_back.setImageResource(R.drawable.goat_64)
                img_door1_back.setImageResource(R.drawable.goat_64)
                img_door3_back.visibility = View.VISIBLE

            }

        }
    }

    fun Select_doors() {

        door0.setOnClickListener {
            Selection0.visibility = View.VISIBLE
            Selection1.visibility = View.GONE
            Selection2.visibility = View.GONE
        }

        door1.setOnClickListener {
            Selection0.visibility = View.GONE
            Selection1.visibility = View.VISIBLE
            Selection2.visibility = View.GONE
        }
        door2.setOnClickListener {
            Selection0.visibility = View.GONE
            Selection1.visibility = View.GONE
            Selection2.visibility = View.VISIBLE
        }
    }


    inner class BtnEvent : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_next ->

                    if (Selection0.visibility == View.GONE && Selection1.visibility == View.GONE && Selection2.visibility == View.GONE) {

                        val tot = Toast.makeText(
                            this@MainActivity,
                            "1개의 문을 선택 해 주세요.",
                            Toast.LENGTH_SHORT
                        )
                        tot.show()
                    } else {


                        if (firstQuestion.visibility == View.VISIBLE) {
                            firstQuestion.visibility = View.GONE
                            SecondQuestion.visibility = View.VISIBLE
                            LastQuestion.visibility = View.GONE


                            if (Selection0.visibility.equals(View.VISIBLE)) {
                                when {
                                    rnds_string.equals(0) -> {  // 0번에 차량이 있다면

                                        val Second_rnds = intArrayOf(1, 2).random()
                                        when (Second_rnds) {
                                            1 -> {
                                                door1.visibility = View.INVISIBLE
                                            }
                                            2 -> {
                                                door2.visibility = View.INVISIBLE
                                            }
                                        }
                                    }
                                    rnds_string.equals(1) -> { // 2번에 차량이 있다면
                                        door2.visibility = View.INVISIBLE
                                    }
                                    rnds_string.equals(2) -> {  //3번에 차가 있을 경우
                                        door1.visibility = View.INVISIBLE

                                    }
                                }
                            } else if (Selection1.visibility.equals(View.VISIBLE)) {
                                when {
                                    rnds_string.equals(1) -> {  // 0번에 차량이 있다면

                                        val Second_rnds = intArrayOf(0, 2).random()
                                        when (Second_rnds) {
                                            0 -> {
                                                door0.visibility = View.INVISIBLE
                                            }
                                            2 -> {
                                                door2.visibility = View.INVISIBLE
                                            }
                                        }
                                    }
                                    rnds_string.equals(0) -> {
                                        door2.visibility = View.INVISIBLE
                                    }
                                    rnds_string.equals(2) -> {
                                        door0.visibility = View.INVISIBLE

                                    }
                                }

                            } else if (Selection2.visibility.equals(View.VISIBLE)) {
                                when {
                                    rnds_string.equals(2) -> {  //

                                        val Second_rnds = intArrayOf(0, 1).random()
                                        when (Second_rnds) {
                                            0 -> {
                                                door0.visibility = View.INVISIBLE
                                            }
                                            1 -> {
                                                door1.visibility = View.INVISIBLE
                                            }
                                        }
                                    }
                                    rnds_string.equals(0) -> {
                                        door1.visibility = View.INVISIBLE
                                    }
                                    rnds_string.equals(1) -> {

                                        door0.visibility = View.INVISIBLE

                                    }
                                }
                            }

                        } else if (SecondQuestion.visibility == View.VISIBLE) {

                            firstQuestion.visibility = View.GONE
                            SecondQuestion.visibility = View.GONE
                            LastQuestion.visibility = View.VISIBLE
                            btn_next.visibility = View.INVISIBLE
                            btn_restart.visibility = View.VISIBLE
                            btn_view_results.visibility = View.VISIBLE

                            door0.visibility = View.INVISIBLE
                            door1.visibility = View.INVISIBLE
                            door2.visibility = View.INVISIBLE


                            if (Selection0.visibility == View.VISIBLE && img_door1_back.equals(R.drawable.supercar_64)) {
                                cont++
                                sum++

                            } else {
                                cont++
                                sum++
                            }
                        }
                    }
                R.id.btn_restart -> {
                    firstQuestion.visibility == View.VISIBLE
                    SecondQuestion.visibility == View.GONE
                    rnds()
                }

            }
        }
    }
}






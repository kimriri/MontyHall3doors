package ko.co.montyhall3doors

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val first_rnds = (0..2).random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (first_rnds) {

            0 -> {
                img_door1_back.setImageResource(R.drawable.supercar_64)
                img_door1_back.visibility = View.VISIBLE

            }
            1 -> {
                img_door2_back.setImageResource(R.drawable.supercar_64)
                img_door2_back.visibility = View.VISIBLE
            }
            else -> {
                img_door3_back.setImageResource(R.drawable.supercar_64)
                img_door3_back.visibility = View.VISIBLE

            }

        }

        Select_doors()
        btn_event()
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


    fun btn_event() {
        btn_next.setOnClickListener {


            if (firstQuestion.visibility == View.VISIBLE) {
                firstQuestion.visibility = View.GONE
                SecondQuestion.visibility = View.VISIBLE


                if (Selection0.visibility.equals(View.VISIBLE)) { // 0번 문을 선택했을때
                    when {
                        first_rnds.equals(0) -> {  // 0번에 차량이 있다면

                            val Second_rnds = intArrayOf(1, 2).random()
                            when (Second_rnds) {
                                1 -> {
                                    door0.visibility = View.INVISIBLE
                    if (Selection0.visibility == View.GONE && Selection1.visibility == View.GONE && Selection2.visibility == View.GONE) {

                        val tot = Toast.makeText(
                            this@MainActivity,
                            "1개의 문을 선택 해 주세요.",
                            Toast.LENGTH_SHORT
                        )
                        tot.show()
                    } else {
                                }
                                2 -> {
                                    door2.visibility = View.INVISIBLE
                                }
                            }
                        }
                        first_rnds.equals(1) -> { // 2번에 차량이 있다면
                            door2.visibility = View.INVISIBLE
                        }
                        first_rnds.equals(2) -> {  //3번에 차가 있을 경우
                            door1.visibility = View.INVISIBLE

                        }
                    }
                } else if (Selection1.visibility.equals(View.VISIBLE)) { // 두번째를 선택 했을 경우 열수 있는건 0번 2번
                    when {
                        first_rnds.equals(1) -> {  // 0번에 차량이 있다면

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
                        first_rnds.equals(0) -> { // 2번에 차량이 있다면
                            door2.visibility = View.INVISIBLE
                        }
                        first_rnds.equals(2) -> {  //3번에 차가 있을 경우
                            door0.visibility = View.INVISIBLE

                        }
                    }

                } else if (Selection2.visibility.equals(View.VISIBLE)) { //3번째 문을 선택 했을때
                    when {
                        first_rnds.equals(2) -> {  //

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
                        first_rnds.equals(0) -> { // 2번에 차량이 있다면
                            door1.visibility = View.INVISIBLE
                        }
                        first_rnds.equals(1) -> {  //3번에 차가 있을 경우

                            door0.visibility = View.INVISIBLE

                        }
                    }

                }

                // 경우의 수
                // 1. 1번 문 뒤에 차가 있는 경우
                // 1번문을 고를 경우
                // 2번 문을 열거나 3번 문을 연다.
                // 2번 문을 고를 경우
                // 3번 문을 연다
                // 3번 문을 고를 경우 2번 문을 연다
                // first_rnds이 1일 경우


                // 2. 2번 문 뒤에 차가 있을 경우
                // 1번 문을 고를 경우
                // 3번 문을 연다
                // 2번 문을 고를 경우 1번 문 혹은 3번 문을 연다
                // 3번 문을 고를 경우 1번 문을 연다

                //3번 문 뒤에 차가 있을 경우
                // 1번문을 고를 경우
                // 2번 문을 연다
                // 2번 문을 고를 경우
                // 1번 문을 연다
                // 3번 문을 고를경우 1번 혹은 2번 문을 연다

            } else if (SecondQuestion.visibility == View.VISIBLE) {
                door0.visibility = View.INVISIBLE
                door1.visibility = View.INVISIBLE
                door2.visibility = View.INVISIBLE

            }


        }

    }

}



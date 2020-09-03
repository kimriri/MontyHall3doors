package ko.co.montyhall3doors

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    var sum: Long = 0
    var cont: Long = 0
    var percentage: Double = 0.0
    var rnds_start = 0
    var Select = 0
    var finalSelect = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_event = BtnEvent();
        btn_next.setOnClickListener(btn_event);
        btn_restart.setOnClickListener(btn_event);
        btn_view_results.setOnClickListener(btn_event)
        Start()

    }

    fun Start() {
        tv_main_question1.visibility = View.INVISIBLE
        tv_main_question2.visibility = View.INVISIBLE
        img_door0.visibility = View.VISIBLE
        img_door1.visibility = View.VISIBLE
        img_door2.visibility = View.VISIBLE
        btn_next.visibility = View.VISIBLE
        btn_view_results.visibility = View.GONE
        btn_restart.visibility = View.GONE
        iv_selection0.visibility = View.GONE
        iv_selection1.visibility = View.GONE
        iv_selection2.visibility = View.GONE
        rnds()
    }

    fun rnds() {
        val first_rnds = (0..2).random()

        rnds_start = first_rnds
        Start_rnds()
        Select_doors()
    }

    fun Start_rnds() {

        when (rnds_start) {

            0 -> {
                img_door0_back.setImageResource(R.drawable.supercar_64)
                img_door1_back.setImageResource(R.drawable.goat_64)
                img_door2_back.setImageResource(R.drawable.goat_64)
                img_door0_back.visibility = View.VISIBLE

            }
            1 -> {
                img_door1_back.setImageResource(R.drawable.supercar_64)
                img_door0_back.setImageResource(R.drawable.goat_64)
                img_door2_back.setImageResource(R.drawable.goat_64)
                img_door1_back.visibility = View.VISIBLE
            }
            else -> {
                img_door2_back.setImageResource(R.drawable.supercar_64)
                img_door1_back.setImageResource(R.drawable.goat_64)
                img_door0_back.setImageResource(R.drawable.goat_64)
                img_door2_back.visibility = View.VISIBLE

            }

        }
    }

    fun Select_doors() {

        img_door0.setOnClickListener {
            iv_selection0.visibility = View.VISIBLE
            iv_selection1.visibility = View.GONE
            iv_selection2.visibility = View.GONE
        }

        img_door1.setOnClickListener {
            iv_selection0.visibility = View.GONE
            iv_selection1.visibility = View.VISIBLE
            iv_selection2.visibility = View.GONE
        }
        img_door2.setOnClickListener {
            iv_selection0.visibility = View.GONE
            iv_selection1.visibility = View.GONE
            iv_selection2.visibility = View.VISIBLE
        }
    }


    @SuppressLint("ShowToast", "SetTextI18n")
    private fun Popup() {

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_popup, null)
        val textView: TextView = view.findViewById(R.id.main_popup)
        val Percent = String.format("%.2f", percentage)

        Log.e("확률은", "$Percent 입니다.")

        textView.text = "총 $cont 번 시도 하여서 약 $Percent 의 확률을 얻었습니다."
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("결과")
            .setPositiveButton("보기") { dialog, which ->
                Toast.makeText(applicationContext, "결과보여주기", Toast.LENGTH_SHORT)
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }


    //   버튼 이벤트 -  > 바꿧는지 바꾸지 않았는지  확인 하기
    // 바꿧다면 바꿧을 때의 확률만 계산하기

    inner class BtnEvent : View.OnClickListener {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("ResourceType")
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_next ->
                    if (iv_selection0.visibility == View.GONE && iv_selection1.visibility == View.GONE && iv_selection2.visibility == View.GONE) {

                        val tot =
                            Toast.makeText(this@MainActivity, "1개의 문을 선택해 주세요", Toast.LENGTH_SHORT)
                        tot.show()

                    } else {

                        if (tv_main_question0.visibility == View.VISIBLE) {
                            tv_main_question0.visibility = View.GONE
                            tv_main_question1.visibility = View.VISIBLE
                            tv_main_question2.visibility = View.GONE


                            when {
                                iv_selection0.visibility.equals(View.VISIBLE) -> {
                                    when {
                                        rnds_start.equals(0) -> {  // 0번에 차가 있을 경우

                                            val Second_rnds = intArrayOf(1, 2).random()
                                            when (Second_rnds) {
                                                1 -> {
                                                    img_door1.visibility = View.INVISIBLE
                                                }
                                                2 -> {
                                                    img_door2.visibility = View.INVISIBLE
                                                }
                                            }
                                        }
                                        rnds_start.equals(1) -> {
                                            img_door2.visibility = View.INVISIBLE
                                        }
                                        rnds_start.equals(2) -> {
                                            img_door1.visibility = View.INVISIBLE

                                        }
                                    }

                                }


                                iv_selection1.visibility.equals(View.VISIBLE) -> {
                                    when {
                                        rnds_start.equals(1) -> {

                                            val Second_rnds = intArrayOf(0, 2).random()
                                            when (Second_rnds) {
                                                0 -> {
                                                    img_door0.visibility = View.INVISIBLE
                                                }
                                                2 -> {
                                                    img_door2.visibility = View.INVISIBLE
                                                }
                                            }
                                        }
                                        rnds_start.equals(0) -> {
                                            img_door2.visibility = View.INVISIBLE
                                        }
                                        rnds_start.equals(2) -> {
                                            img_door0.visibility = View.INVISIBLE

                                        }
                                    }

                                }
                                iv_selection2.visibility.equals(View.VISIBLE) -> {
                                    when {
                                        rnds_start.equals(2) -> {

                                            val Second_rnds = intArrayOf(0, 1).random()
                                            when (Second_rnds) {
                                                0 -> {
                                                    img_door0.visibility = View.INVISIBLE
                                                }
                                                1 -> {
                                                    img_door1.visibility = View.INVISIBLE
                                                }
                                            }
                                        }
                                        rnds_start.equals(0) -> {
                                            img_door1.visibility = View.INVISIBLE
                                        }
                                        rnds_start.equals(1) -> {
                                            img_door0.visibility = View.INVISIBLE
                                        }
                                    }

                                }
                            }

                            when (View.VISIBLE) {
                                iv_selection0.visibility -> {
                                    Select = 0
                                }
                                iv_selection1.visibility -> {

                                    Select = 1
                                }
                                iv_selection2.visibility -> {

                                    Select = 2
                                }

                            }
                            Log.e("파이널", "$Select 1입니다")
                        } else if (tv_main_question1.visibility == View.VISIBLE) {

                            // 숫자가 바꼇는지 확인하고 바꼇으면 sum ++ 를 해준다 .


                            tv_main_question0.visibility = View.GONE
                            tv_main_question1.visibility = View.GONE
                            tv_main_question2.visibility = View.VISIBLE
                            btn_next.visibility = View.INVISIBLE
                            btn_restart.visibility = View.VISIBLE
                            btn_view_results.visibility = View.VISIBLE

                            img_door0.visibility = View.INVISIBLE
                            img_door1.visibility = View.INVISIBLE
                            img_door2.visibility = View.INVISIBLE


                            val drawable = getDrawable(R.drawable.supercar_64)
                            val bitmapDrawable = drawable as BitmapDrawable
                            val bitmap = bitmapDrawable.bitmap

                            val drawable1 = img_door0_back.drawable
                            val bitmapDrawable1 = drawable1 as BitmapDrawable
                            val bitmap1 = bitmapDrawable1.bitmap

                            val drawable2 = img_door1_back.drawable
                            val bitmapDrawable2 = drawable2 as BitmapDrawable
                            val bitmap2 = bitmapDrawable2.bitmap

                            val drawable3 = img_door2_back.drawable
                            val bitmapDrawable3 = drawable3 as BitmapDrawable
                            val bitmap3 = bitmapDrawable3.bitmap


                            when (View.VISIBLE) {
                                iv_selection0.visibility -> {
                                    finalSelect = 0
                                }
                                iv_selection1.visibility -> {

                                    finalSelect = 1
                                }
                                iv_selection2.visibility -> {

                                    finalSelect = 2

                                }

                            }
                            Log.e("파이널", "$finalSelect 입니다  ")
                            if (iv_selection0.visibility == View.VISIBLE && bitmap == bitmap1) {
                                cont++
                                if (Select != finalSelect ) {

                                    Log.e("파이널", "$Select 는 $Select 과 $finalSelect  입니다 1")
                                    sum++
                                }
                                Log.e("sum", "$sum 개")
                            } else if (iv_selection1.visibility == View.VISIBLE && bitmap == bitmap2) {
                                cont++
                                if (Select != finalSelect) {

                                    sum++

                                    Log.e("파이널", "$Select 과 $finalSelect  입니다 2")
                                }
                                Log.e("sum", "$sum 개")
                            } else if (iv_selection2.visibility == View.VISIBLE && bitmap == bitmap3) {
                                cont++
                                if (Select != finalSelect) {
                                    sum++
                                    Log.e("파이널", "$Select 과 $finalSelect  입니다3")
                                }
                            } else {
                                cont++

                            }
                            Log.e("count", "$cont 개")
                        }
                    }


                R.id.btn_restart -> {
                    tv_main_question2.visibility = View.INVISIBLE
                    tv_main_question0.visibility = View.VISIBLE
                    Start()
                }

                R.id.btn_view_results -> {

                    percentage = (sum.toDouble() / cont.toDouble()) * 100

                    Log.e("확률0은", " sum 은  $sum 이고  cont는 $cont 입니다.  $percentage 입니다.")

                    Popup()
                }
            }
        }
    }
}






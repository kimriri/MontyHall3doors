package ko.co.montyhall3doors

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_auto_play_popup.*


class MainActivity : AppCompatActivity() {

    var MainNoNModifind: Long = 0
    var MainModifind: Long = 0
    var MainSum: Long = 0
    var MainSum2: Long = 0
    var MainCont: Long = 0
    var MainPercentage: Double = 0.0
    var MainPercentage2: Double = 0.0
    var MainRandomStart = 0
    var MainAutoRandomStart0 = 0
    var MainAutoRandomStart1 = 0
    var MainSelect = 0
    var MainFinalSelect = 0
    var MainSupCar = 0
    var MainAutoPopCount = 0
    var MainAutoPopNum2 = 0
    var MainAutoSum = 0
    var MainAutoPercentage: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        RandomNumberGenerator()
        ButtonEventView()
    }

    private fun ButtonEventView() {
        btn_next.setOnClickListener {
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
                                MainRandomStart.equals(0) -> {

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
                                MainRandomStart.equals(1) -> {
                                    img_door2.visibility = View.INVISIBLE
                                }
                                MainRandomStart.equals(2) -> {
                                    img_door1.visibility = View.INVISIBLE

                                }
                            }
                        }

                        iv_selection1.visibility.equals(View.VISIBLE) -> {
                            when {
                                MainRandomStart.equals(1) -> {

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
                                MainRandomStart.equals(0) -> {
                                    img_door2.visibility = View.INVISIBLE
                                }
                                MainRandomStart.equals(2) -> {
                                    img_door0.visibility = View.INVISIBLE
                                }
                            }
                        }
                        iv_selection2.visibility.equals(View.VISIBLE) -> {
                            when {
                                MainRandomStart.equals(2) -> {

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
                                MainRandomStart.equals(0) -> {
                                    img_door1.visibility = View.INVISIBLE
                                }
                                MainRandomStart.equals(1) -> {
                                    img_door0.visibility = View.INVISIBLE
                                }
                            }
                        }
                    }
                    when (View.VISIBLE) {
                        iv_selection0.visibility -> {

                            MainSelect = 0
                        }
                        iv_selection1.visibility -> {

                            MainSelect = 1
                        }
                        iv_selection2.visibility -> {

                            MainSelect = 2
                        }
                    }
                    Log.e("파이널", "두번째 질문에서의 Select 는 $MainSelect 입니다")
                } else if (tv_main_question1.visibility == View.VISIBLE) {


                    tv_main_question0.visibility = View.GONE
                    tv_main_question1.visibility = View.GONE
                    tv_main_question2.visibility = View.VISIBLE
                    btn_next.visibility = View.INVISIBLE
                    btn_restart.visibility = View.VISIBLE
                    btn_view_results.visibility = View.VISIBLE

                    img_door0.visibility = View.INVISIBLE
                    img_door1.visibility = View.INVISIBLE
                    img_door2.visibility = View.INVISIBLE

                    when (View.VISIBLE) {
                        iv_selection0.visibility -> {
                            MainFinalSelect = 0
                        }
                        iv_selection1.visibility -> {

                            MainFinalSelect = 1
                        }
                        iv_selection2.visibility -> {

                            MainFinalSelect = 2

                        }

                    }
                    Log.e("파이널", "finalSelect 는 $MainFinalSelect 입니다  ")
                    // 시도한 전체 횟수를 더해준다.
                    MainCont++

                    if (MainSelect != MainFinalSelect) {
                        //선택을 바꾸었을때의 합계
                        MainModifind++
                        Log.e(
                            "확률은",
                            " MainModifind 은  $MainModifind 입니다."
                        )
                        if (iv_selection0.visibility == View.VISIBLE && MainSupCar == 0
                            || iv_selection1.visibility == View.VISIBLE && MainSupCar == 1
                            || iv_selection2.visibility == View.VISIBLE && MainSupCar == 2
                        ) {
                            // 선택을 바꾸었을때 자동차 이미지인 경우
                            MainSum++
                            Log.e(
                                "확률은",
                                " MainSum 은  $MainSum 입니다."
                            )
                        }

                    } else if (MainSelect == MainFinalSelect) {
                        // 선택이 바뀌지 않았을때의 합계
                        MainNoNModifind++
                        Log.e(
                            "확률은",
                            " MainNoNModifind 은  $MainNoNModifind 입니다."
                        )
                        if (iv_selection0.visibility == View.VISIBLE && MainSupCar == 0
                            || iv_selection1.visibility == View.VISIBLE && MainSupCar == 1
                            || iv_selection2.visibility == View.VISIBLE && MainSupCar == 2
                        ) {
                            // 선택을 바꾸었을때 자동차 이미지인 경우
                            MainSum2++
                            Log.e(
                                "확률은",
                                " MainSum2 은  $MainSum2 입니다."
                            )
                        }
                    }
                }
            }
        }
        btn_restart.setOnClickListener {
            tv_main_question2.visibility = View.INVISIBLE
            tv_main_question0.visibility = View.VISIBLE
            initializeView()

        }
        btn_view_results.setOnClickListener {
            //MainPercentage 선택이 바뀌었을때의 합계
            //MainPercentage2 선택이 바뀌지 않았을때의 확률

            MainPercentage = (MainSum.toDouble() / MainModifind.toDouble()) * 100
            MainPercentage2 = (MainSum2.toDouble() / MainNoNModifind.toDouble()) * 100


            if (MainPercentage.isNaN()) {
                MainPercentage = 0.00
            }

            if (MainPercentage2.isNaN()) {
                MainPercentage2 = 0.00
            }
            Log.e(
                "확률은",
                " MainPercentage 은  $MainPercentage 이고 MainPercentage2 는 $MainPercentage2, MainPercentage 은 $MainPercentage, MainNoNModifind 은 $MainNoNModifind MainSum2는 $MainSum2 입니다."
            )
            MainPopup()
        }
        btn_main_auto_play.setOnClickListener {
            MainAutoPopup0()

        }
    }


    private fun initializeView() {

        img_door0.visibility = View.VISIBLE
        img_door1.visibility = View.VISIBLE
        img_door2.visibility = View.VISIBLE
        btn_next.visibility = View.VISIBLE
        btn_view_results.visibility = View.GONE
        btn_restart.visibility = View.GONE
        iv_selection0.visibility = View.GONE
        iv_selection1.visibility = View.GONE
        iv_selection2.visibility = View.GONE
        RandomNumberGenerator()
    }

    private fun RandomNumberGenerator() {
        val MainFirstRandom = (0..2).random()
        MainRandomStart = MainFirstRandom
        MainInputRandom()
        MainSelectDoors()
    }

    private fun RandomAutoNumberGenerator() {

        for (item in 1..MainAutoPopCount) {
            val MainAutoRandom1 = (0..2).random()
            val MainAutoRandom2 = (0..2).random()
            MainAutoRandomStart0 = MainAutoRandom1
            MainAutoRandomStart1 = MainAutoRandom2

            when (MainAutoRandomStart0 != MainAutoRandomStart1) {
                true -> MainAutoSum++
            }

        }
    }

    private fun MainInputRandom() {  //  문뒤에 랜덤 이미지 삽입

        when (MainRandomStart) {

            0 -> {
                img_door0_back.setImageResource(R.drawable.supercar_64)
                img_door1_back.setImageResource(R.drawable.goat_64)
                img_door2_back.setImageResource(R.drawable.goat_64)
                img_door0_back.visibility = View.VISIBLE
                MainSupCar = 0

            }
            1 -> {
                img_door1_back.setImageResource(R.drawable.supercar_64)
                img_door0_back.setImageResource(R.drawable.goat_64)
                img_door2_back.setImageResource(R.drawable.goat_64)
                img_door1_back.visibility = View.VISIBLE
                MainSupCar = 1
            }
            else -> {
                img_door2_back.setImageResource(R.drawable.supercar_64)
                img_door1_back.setImageResource(R.drawable.goat_64)
                img_door0_back.setImageResource(R.drawable.goat_64)
                img_door2_back.visibility = View.VISIBLE
                MainSupCar = 2

            }

        }
    }


    private fun MainSelectDoors() {  // 선택 버튼을 보여주는 람수

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
    private fun MainPopup() {

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_popup, null)
        val TVMainPopup: TextView = view.findViewById(R.id.tv_main_popup)
        val MainPopupPercent = String.format("%.2f", MainPercentage)
        val MainPopupPercent2 = String.format("%.2f", MainPercentage2)

        TVMainPopup.text =
            "총 $MainCont 번의 시도중 $MainModifind 번 선택을 번복 하였으며 선택 번복시 성공 확률은 $MainPopupPercent, 번복하지 않을경우 $MainPopupPercent2 의 확률을 얻었습니다."
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("결과")
            .setPositiveButton("보기") { _, _ ->
                Toast.makeText(applicationContext, "결과 보여주기", Toast.LENGTH_SHORT)
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun MainAutoPopup1() {

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_popup, null)
        val TVMainPopup: TextView = view.findViewById(R.id.tv_main_popup)
        val MainPopupPercent = String.format("%.2f", MainAutoPercentage)

        TVMainPopup.text =
            "총 $MainAutoPopCount 번의 시도중 $MainAutoSum 번 성공 하여 $MainPopupPercent 의 확률을 얻었습니다. "
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("결과")
            .setPositiveButton("보기") { _, _ ->
                Toast.makeText(applicationContext, "결과 보여주기", Toast.LENGTH_SHORT)
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun MainAutoPopup0() {

        val builder = AlertDialog.Builder(this)
        // builder.setIcon(R.mipmap.ic_launcher)

        builder.setTitle("자동 실행")
        builder.setView(tv_main_auto_popup_auto)
        val v1 = layoutInflater.inflate(R.layout.main_auto_play_popup, null)
        builder.setView(v1)

        // p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용
        val listener = DialogInterface.OnClickListener { p0, p1 ->
            val alert = p0 as AlertDialog
            val textView: TextView? = alert.findViewById<TextView>(R.id.tv_main_auto_popup_auto)
            val edit1: EditText? = alert.findViewById<EditText>(R.id.tv_main_auto_popup_number)
            tv1.text = "${edit1?.text}"
            tv1.visibility = View.GONE
            if (tv1.text == "") {
                Log.e("횟수", "선택 변경 인 경우 횟수는 $tv1 입니다.  $edit1?.text")
                val toast =
                    Toast.makeText(this@MainActivity, "자동실행할 횟수를 입력해 주세요.", Toast.LENGTH_SHORT)
                toast.show()
                MainAutoPopup0()
            } else {
                MainAutoPopCount = Integer.parseInt(tv1.text.toString())
                Log.e("횟수", "선택 변경 인 경우 횟수는 $MainAutoPopCount 입니다.")

                initializeView()  // 뷰 초기화
                RandomAutoNumberGenerator()  // 램덤 반복 으로 실행 하기
                MainAutoPercentage = (MainAutoSum.toDouble() / MainAutoPopCount.toDouble()) * 100
                Log.e("확인인", "램덤 선택버튼은 $MainAutoPercentage 입니다. 총 시도 횟수는 $MainAutoPopCount  입니다. MainAutoPopCount는 $MainAutoPopCount MainAutoSum은 $MainAutoSum 입니다.")
                MainAutoPopup1()
            }
        }
            val listener1 = DialogInterface.OnClickListener { p0, p1 ->
            val alert = p0 as AlertDialog
            val edit1: EditText? = alert.findViewById<EditText>(R.id.tv_main_auto_popup_number)

            tv2.text = "${edit1?.text}"
            tv2.visibility = View.GONE
            MainAutoPopNum2 = Integer.parseInt(tv2.toString())
            Log.e("횟수", "선택 미 변경 인 경우 횟수는 $MainAutoPopNum2 입니다.")
        }


        when (tv1.text) {
            null -> {
                builder.setPositiveButton("미번복", null)
            }
            else ->
                builder.setPositiveButton("미번복", listener1)
        }
        when (tv2.text) {
            null -> {
                builder.setPositiveButton("번복", null)
            }
            else ->
                builder.setNegativeButton("번복 ", listener)
        }
        builder.setNeutralButton(
            "돌아가기"
        ) { _, _ ->
        }
        builder.show()


    }


}


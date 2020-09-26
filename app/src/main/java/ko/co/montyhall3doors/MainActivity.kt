package ko.co.montyhall3doors

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_auto_play_popup.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var MainNoNModifind: Long = 0
    var MainModifind: Long = 0
    var MainChangeSum: Long = 0
    var MainNotChangeSum: Long = 0
    var MainCont: Long = 0
    var MainChangePercentage: Double = 0.0
    var MainNotChangePercentage: Double = 0.0
    var MainRandomCar = 0
    var MainAutoRandomCar = 0
    var MainAutoRandomMySelection = 0
    var MainSelect = 0
    var MainFinalSelect = 0
    var MainSupCar = 0
    var MainAutoPopCount = 0
    var MainAutoNotChangePopNum = 0
    var MainChangeAutoSum = 0
    var MainNotChangeAutoSum = 0
    var MainAutoAllPercentage: Double = 0.0
    var MainAutoEachPercentage: Double = 0.0

    val autodataList = ArrayList<AutoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fabric.with(this, Crashlytics())
        initializeView()
        RandomNumberGenerator()
        ButtonEventView()

//        val crashButton = Button(this)
//        crashButton.setText("Crash!")
//        crashButton.setOnClickListener(View.OnClickListener {
//            Crashlytics.getInstance().crash() // Force a crash
//        })
//        addContentView(
//            crashButton,
//            ActionBar.LayoutParams(
//                ActionBar.LayoutParams.MATCH_PARENT,
//                ActionBar.LayoutParams.WRAP_CONTENT
//            )
//        )
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
                                MainRandomCar.equals(0) -> {

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
                                MainRandomCar.equals(1) -> {
                                    img_door2.visibility = View.INVISIBLE
                                }
                                MainRandomCar.equals(2) -> {
                                    img_door1.visibility = View.INVISIBLE

                                }
                            }
                        }

                        iv_selection1.visibility.equals(View.VISIBLE) -> {
                            when {
                                MainRandomCar.equals(1) -> {

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
                                MainRandomCar.equals(0) -> {
                                    img_door2.visibility = View.INVISIBLE
                                }
                                MainRandomCar.equals(2) -> {
                                    img_door0.visibility = View.INVISIBLE
                                }
                            }
                        }
                        iv_selection2.visibility.equals(View.VISIBLE) -> {
                            when {
                                MainRandomCar.equals(2) -> {

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
                                MainRandomCar.equals(0) -> {
                                    img_door1.visibility = View.INVISIBLE
                                }
                                MainRandomCar.equals(1) -> {
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
                            MainChangeSum++
                            Log.e(
                                "확률은",
                                " MainSum 은  $MainChangeSum 입니다."
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
                            MainNotChangeSum++
                            Log.e(
                                "확률은",
                                " MainSum2 은  $MainNotChangeSum 입니다."
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

            MainChangePercentage = (MainChangeSum.toDouble() / MainModifind.toDouble()) * 100
            MainNotChangePercentage = (MainNotChangeSum.toDouble() / MainNoNModifind.toDouble()) * 100


            if (MainChangePercentage.isNaN()) {
                MainChangePercentage = 0.00
            }

            if (MainNotChangePercentage.isNaN()) {
                MainNotChangePercentage = 0.00
            }
            Log.e(
                "확률은",
                " MainPercentage 은  $MainChangePercentage 이고 MainPercentage2 는 $MainNotChangePercentage, MainPercentage 은 $MainChangePercentage, MainNoNModifind 은 $MainNoNModifind MainSum2는 $MainNotChangeSum 입니다."
            )
            MainPopup()
        }
        btn_main_auto_play.setOnClickListener {
            MainAutoPopup()

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
        MainRandomCar = MainFirstRandom
        MainInputRandom()
        MainSelectDoors()
    }

    private fun RandomAutoNumberGeneratorChange() {
        autodataList.clear()
        MainChangeAutoSum = 0

        for (item in 1..MainAutoPopCount) {
            val MainAutoRandom1 = (0..2).random() // 0
            val MainAutoRandom2 = (0..2).random()  // 1
            var SuccessOrNot: Boolean = true
            MainAutoRandomCar = MainAutoRandom1
            MainAutoRandomMySelection = MainAutoRandom2

            when (MainAutoRandomCar != MainAutoRandomMySelection) {

                true -> {
                    MainChangeAutoSum++
                    SuccessOrNot
                }
                false -> SuccessOrNot = !SuccessOrNot
            }
            MainAutoEachPercentage = (MainChangeAutoSum.toDouble() / item.toDouble()) * 100


            val strAutoPopCount: String = item.toString()  // 전체
            val strAutoSum: String = MainChangeAutoSum.toString()  // 성공 횟수

            val strAutoPercentage2 = String.format("%.2f", MainAutoEachPercentage) // //  개별 확률
            val nextIntent = Intent(this, ListActivity::class.java)
            nextIntent.putExtra("AutoPopCount", item)
            nextIntent.putExtra("AutoSum", strAutoSum)
            nextIntent.putExtra("strAutoPercentage2", strAutoPercentage2)
            nextIntent.putExtra("strSuccessOrNot", SuccessOrNot)
            val autodataList1 =
                AutoData(strAutoPopCount, strAutoSum, "$strAutoPercentage2%", SuccessOrNot)
            autodataList.add(autodataList1)
        }
    }

    private fun RandomAutoNumberGeneratorNotChange() {
        autodataList.clear()
        MainNotChangeAutoSum = 0
        for (item in 1..MainAutoPopCount) {
            val MainAutoRandom1 = (0..2).random()
            val MainAutoRandom2 = (0..2).random()
            var SuccessOrNot: Boolean = true
            MainAutoRandomCar = MainAutoRandom1
            MainAutoRandomMySelection = MainAutoRandom2

            when (MainAutoRandomCar == MainAutoRandomMySelection) {
                true -> {
                    MainNotChangeAutoSum++
                    SuccessOrNot
                }
                false -> SuccessOrNot = !SuccessOrNot
            }
            MainAutoEachPercentage = (MainNotChangeAutoSum.toDouble() / item.toDouble()) * 100


            val strAutoPopCount: String = item.toString()  // 전체
            val strAutoSum2: String = MainNotChangeAutoSum.toString()  // 성공 횟수
            val strAutoPercentage2 = String.format("%.2f", MainAutoEachPercentage) // //  개별 확률
            val nextIntent = Intent(this, ListActivity::class.java)
            nextIntent.putExtra("AutoPopCount", item)
            nextIntent.putExtra("AutoSum", strAutoSum2)
            nextIntent.putExtra("strAutoPercentage2", strAutoPercentage2)
            nextIntent.putExtra("strSuccessOrNot", SuccessOrNot)
            val autodataList1 =
                AutoData(strAutoPopCount, strAutoSum2, "$strAutoPercentage2%", SuccessOrNot)
            autodataList.add(autodataList1)
        }
    }


    private fun MainInputRandom() {  //  문뒤에 랜덤 이미지 삽입

        when (MainRandomCar) {

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


    private fun MainPopup() {

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_popup, null)
        val TVMainPopup: TextView = view.findViewById(R.id.tv_main_popup)
        val MainPopupPercent = String.format("%.2f", MainChangePercentage)
        val MainPopupPercent2 = String.format("%.2f", MainNotChangePercentage)

        TVMainPopup.text =
            "총 $MainCont 번의 시도중 $MainModifind 번 선택을 번복 하였으며 선택 번복시 성공 확률은 $MainPopupPercent, 번복하지 않을경우 $MainPopupPercent2 의 확률을 얻었습니다."

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("결과")
            .setNegativeButton("확인", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun MainAutoPopupChange() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_popup, null)
        val TVMainPopup: TextView = view.findViewById(R.id.tv_main_popup)
        val MainPopupPercent = String.format("%.2f", MainAutoAllPercentage)

        val listener3 = DialogInterface.OnClickListener { p0, p1 ->
            val alert = p0 as AlertDialog

            MainAutoNotChangePopNum = Integer.parseInt(tv2.toString())
            Log.e("횟수", "선택 미 변경 인 경우 횟수는 $MainAutoNotChangePopNum 입니다.")
        }
        TVMainPopup.text =
            "총 $MainAutoPopCount 번의 시도중 $MainChangeAutoSum 번 성공 하여 $MainPopupPercent 의 확률을 얻었습니다. "
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("결과")
            .setPositiveButton("개별 확률 보기") { _, _ ->
                Toast.makeText(applicationContext, "결과 보여주기", Toast.LENGTH_SHORT)
                val AutoPlayIntent = Intent(this, ListActivity::class.java)
                AutoPlayIntent.putParcelableArrayListExtra("AutoDataList", autodataList)
                startActivity(AutoPlayIntent)
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun MainAutoPopupNotChange() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_popup, null)
        val TVMainPopup: TextView = view.findViewById(R.id.tv_main_popup)
        val MainPopupPercent = String.format("%.2f", MainAutoAllPercentage)

        val listener3 = DialogInterface.OnClickListener { p0, p1 ->
            val alert = p0 as AlertDialog

            MainAutoNotChangePopNum = Integer.parseInt(tv2.toString())
            Log.e("횟수", "선택 미 변경 인 경우 횟수는 $MainAutoNotChangePopNum 입니다.")
        }
        TVMainPopup.text =
            "총 $MainAutoPopCount 번의 시도중 $MainNotChangeAutoSum 번 성공 하여 $MainPopupPercent 의 확률을 얻었습니다. "
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("결과")
            .setPositiveButton("개별 확률 보기") { _, _ ->
                Toast.makeText(applicationContext, "결과 보여주기", Toast.LENGTH_SHORT)
                val AutoPlayIntent = Intent(this, ListActivity::class.java)
                AutoPlayIntent.putParcelableArrayListExtra("AutoDataList", autodataList)
                startActivity(AutoPlayIntent)
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun MainAutoPopup() {

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
                MainAutoPopup()
            } else {
                MainAutoPopCount = Integer.parseInt(tv1.text.toString())
                Log.e("횟수", "선택 변경 인 경우 횟수는 $MainAutoPopCount 입니다.")

                initializeView()  // 뷰 초기화
                RandomAutoNumberGeneratorChange()  // 램덤 반복 으로 실행 하기
                MainAutoAllPercentage = (MainChangeAutoSum.toDouble() / MainAutoPopCount.toDouble()) * 100
                Log.e(
                    "확인인",
                    "램덤 선택버튼은 $MainAutoAllPercentage 입니다. 총 시도 횟수는 $MainAutoPopCount  입니다. MainAutoPopCount는 $MainAutoPopCount MainAutoSum은 $MainChangeAutoSum 입니다."
                )
                MainAutoPopupChange()
            }
        }
        val listener1 = DialogInterface.OnClickListener { p0, p1 ->
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
                MainAutoPopup()
            } else {
                MainAutoPopCount = Integer.parseInt(tv1.text.toString())
                Log.e("횟수", "선택  미변경 인 경우 횟수는 $MainAutoPopCount 입니다.")

                initializeView()  // 뷰 초기화
                RandomAutoNumberGeneratorNotChange()  // 램덤 반복 으로 실행 하기
                MainAutoAllPercentage = (MainNotChangeAutoSum.toDouble() / MainAutoPopCount.toDouble()) * 100
                Log.e(
                    "확인인",
                    "램덤 선택버튼은 $MainAutoAllPercentage 입니다. 총 시도 횟수는 $MainAutoPopCount  입니다. MainAutoPopCount는 $MainAutoPopCount MainAutoSum은 $MainNotChangeAutoSum 입니다."
                )
                MainAutoPopupNotChange()
            }
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


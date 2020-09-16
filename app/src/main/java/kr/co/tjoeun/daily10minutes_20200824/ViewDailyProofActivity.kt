package kr.co.tjoeun.daily10minutes_20200824

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_daily_proof.*
import java.util.*

class ViewDailyProofActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_daily_proof)

        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        selectDateBtn.setOnClickListener {

            val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

//                일단 Calendar 변수를 하나 생성, calendar 객체(Instatnce)를 담아두자
                val selectedDate = Calendar.getInstance()

//                selectedDate에는 몇년 몇월 몇일이 들어 있나? 기본값 : 현재 일시가 자동으로 기록됨

//                날짜가 선택되면 실행해줄 이벤트 코드
//                => 년도 / 월 / 일 각각의 항목을 변경. (selectedDate 변수의 값 변경)

                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

//                년/월/일을 한꺼번에 세팅
                selectedDate.set(year, month, dayOfMonth)

                Log.d("선택한 년", selectedDate.get(Calendar.YEAR).toString())
                Log.d("선택한 월", selectedDate.get(Calendar.MONTH).toString())
                Log.d("선택한 일", selectedDate.get(Calendar.DAY_OF_MONTH).toString())


//                실생활 : 1 ~ 12월
//                자바/ 코틀린 : 0 ~ 11월
            }, 2020, Calendar.SEPTEMBER, 15)
            datePickerDialog.show()

        }
    }

    override fun setValues() {
    }
}
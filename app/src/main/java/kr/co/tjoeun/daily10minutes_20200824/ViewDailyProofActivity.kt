package kr.co.tjoeun.daily10minutes_20200824

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//                날짜가 선택되면 실행해줄 이벤트 코드


//                실생활 : 1 ~ 12월
//                자바/ 코틀린 : 0 ~ 11월
            }, 2020, Calendar.SEPTEMBER, 15)
            datePickerDialog.show()

        }
    }

    override fun setValues() {
    }
}
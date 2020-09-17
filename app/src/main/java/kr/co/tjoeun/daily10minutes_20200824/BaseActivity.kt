package kr.co.tjoeun.daily10minutes_20200824

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        액션바를 꺼내올수 있는 상황일때는 커스텀 액션바로 세팅
        supportActionBar?.let {
            setCustomActionBar()
        }
        /*if (supportActionBar != null){
            setCustomActionBar()
        }*/
    }

    fun setCustomActionBar(){

//       이 화면에 달리 ㄴ액션바가 절대 null 아니라고 하면서 가져옴
        val myActionBar = supportActionBar!!

//        액션바를 커스텀으로 사용 세팅
        myActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

//        액션바에 보여줄 커스텀으로 그린 화면 배치
        myActionBar.setCustomView(R.layout.my_custom_actionbar)

//        커스텀액션바 뒤의 기본 색 제거 => 액션바를 들고 있는 툴바의 좌우 여백을 0으로 설정하자
        val parentToolbar = myActionBar.customView.parent as Toolbar
        parentToolbar.setContentInsetsAbsolute(0,0)

    }
}
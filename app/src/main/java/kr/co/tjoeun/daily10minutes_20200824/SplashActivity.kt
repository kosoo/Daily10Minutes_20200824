package kr.co.tjoeun.daily10minutes_20200824

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kr.co.tjoeun.daily10minutes_20200824.utils.ContextUtil
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

//            자동로그인 된 상황 -> 메인으로
//            자동로그인 체크박스 true and 로그인토근이 있을경우
//            로그인 필요? -> 로그인 화면으로
            val myIntent : Intent
            if (ContextUtil.getAutoLoginCheck(mContext) && ContextUtil.getLoginUserToken(mContext) !=""){
//                자동로그인 OK
                myIntent = Intent(mContext, MainActivity::class.java)
            }else{
                myIntent = Intent(mContext, LoginActivity::class.java)
            }

            startActivity(myIntent)
            finish()
        },2500)
    }
}
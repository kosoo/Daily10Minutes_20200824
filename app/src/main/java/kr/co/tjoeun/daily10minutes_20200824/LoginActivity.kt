package kr.co.tjoeun.daily10minutes_20200824

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ContextUtil
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            Log.d("자동로그인체크여부", isChecked.toString())

            ContextUtil.setAutoLoginCheck(mContext, isChecked)
        }

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }


        loginBtn.setOnClickListener {

            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//
            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    실제 응답이 돌아왔을때 실행시켜줄 내용
                    
                    val codeNum = json.getInt("code")
                    
                    if (codeNum == 200){
//                        서버 개발자가 => 로그인 성공일때는 code를 200으로 준다.
                        Log.d("로그인 시도", "성공 상황")

                        //val data = json.getJSONObject("data")
                        //val user = data.getJSONObject("user")
                        //val nick_name = user.getString("nick_name")

//                        토스트도 일종의  UI 영향 코드  => runOnUiThread 실행
//                        runOnUiThread {
//                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        }

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

//                        메인으로 이동 후에는 로그인화면은 종료하자
                        finish()

                    } else {
//                        로그인 실패
                        Log.e("로그인 시도","실패상황")

                        val message = json.getString("message")
//
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            })


        }
    }

    override fun setValues() {

        autoLoginCheckBox.isChecked = ContextUtil.getAutoLoginCheck(mContext)

    }
}
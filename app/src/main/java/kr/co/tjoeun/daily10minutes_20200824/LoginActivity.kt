package kr.co.tjoeun.daily10minutes_20200824

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
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

                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val nick_name = user.getString("nick_name")

//                        토스트도 일종의  UI 영향 코드  => runOnUiThread 실행
                        runOnUiThread {
                            Toast.makeText(mContext, "닉네임 : ${nick_name}", Toast.LENGTH_SHORT).show()
                        }
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
    }
}
package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject
import kotlin.math.log

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    //val messageStr = json.getString("message")
                    
                    if (codeNum == 200){
//                        서버 개발자가 => 로그인 성공일때는 code를 200으로 준다.
                        Log.d("로그인 시도", "성공 상황")
//                        토스트도 일종의  UI 영향 코드  => runOnUiThread 실행 
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인성공", Toast.LENGTH_SHORT).show()
                        }
                    } else {
//                        로그인 실패
                        Log.e("로그인 시도","실패상황")
//
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            })


        }
    }

    override fun setValues() {
    }
}
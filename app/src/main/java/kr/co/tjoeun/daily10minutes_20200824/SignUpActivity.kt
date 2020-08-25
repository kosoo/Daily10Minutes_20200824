package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
//        비밀번호 입력칸의 내용 변경된 경우
//        입력된 비번의 길이에 따른 문구 출력
//        0글자 : "비밀번호를 입력해주세요"
//        8글자 미만 : "비밀번호가 너무 짦습니다."
//        8글자 이상 : "사용해도 좋은 비밀번호 입니다."
        signUpEmailEdt.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val inputPassword = p0.toString()

                if (inputPassword.length == 0 ){
                    passwordCheckResultTxt.text = "비밀번호를 입력해주세요"
                } else if (inputPassword.length < 8) {
                    passwordCheckResultTxt.text = "비밀번호가 너무 짦습니다."
                } else {
                    passwordCheckResultTxt.text = "사용해도 좋은 비밀번호 입니다."
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


        
//        이메일 입력칸의 내용이 변경된 경우 => 중복검사를 다시 하도록 유도
        signUpEmailEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("입력문구", p0.toString())
                
                emailCheckResultTxt.text = "중복 확인을 해주세요"
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        emailCheckBtn.setOnClickListener {
//            이메일 확인 => 중복검사 => 결과에 문구 반영
            val inputEmail = signUpEmailEdt.text.toString()

            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val code = json.getInt("code")

                    runOnUiThread {
                        if (code == 200){
                            emailCheckResultTxt.text = "사용해도 좋은 이메일입니다."
                        }else{
                            emailCheckResultTxt.text = "중복된 이메일입니다."
                        }
                    }
                }
            })
        }

    }

    override fun setValues() {
    }
}
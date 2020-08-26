package kr.co.tjoeun.daily10minutes_20200824

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

//    아이디 중복검사 통과 여부
    var isIdOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {



        signUpBtn.setOnClickListener {

//            아이디를 사용해도 되는지? (중복검사)
            if (!isIdOk) {
//                사용하면 안되는 경우 회원가입 이벤트 강제 종료
                Toast.makeText(mContext,  "아이디 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            비밀번호를 써되는지?
            if (signUpPasswordEdt.text.length < 8){
                Toast.makeText(mContext,"비밀번호는 8글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            사용해도 된다면 다음 로직 진행
//            닉네임은 한번 정하면 변경이 불가능합니다. 정말 회원가입 하시겠습니까?
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("회원가입 안내")
            alert.setMessage("닉네임은 한번 정하면 변경이 불가능합니다. 정말 회원가입 하시겠습니까?")
            alert.setPositiveButton("확인",
//                실제 히원가입 기능 (API) 호출
            alert.setNegativeButton("취소",  null)
            alert.show()

        }


//        비밀번호 입력칸의 내용 변경된 경우
//        입력된 비번의 길이에 따른 문구 출력
//        0글자 : "비밀번호를 입력해주세요"
//        8글자 미만 : "비밀번호가 너무 짦습니다."
//        8글자 이상 : "사용해도 좋은 비밀번호 입니다."
        signUpPasswordEdt.addTextChangedListener(object :TextWatcher {
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
                isIdOk = false
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
                            isIdOk = true
                        }else{
                            emailCheckResultTxt.text = "중복된 이메일입니다."
                            isIdOk = false
                        }
                    }
                }
            })
        }



    }

    override fun setValues() {
    }
}
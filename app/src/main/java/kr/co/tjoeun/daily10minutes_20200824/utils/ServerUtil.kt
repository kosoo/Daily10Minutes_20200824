package kr.co.tjoeun.daily10minutes_20200824.utils

import okhttp3.*
import java.io.IOException
import java.util.*

class ServerUtil {

    companion object{
//        이 영역안에 만드는 변수 또는 함수는 객체를 이용하지 않고, 클래스 자체의 기능으로 활용 가능 (java의 static 처럼 활용 가능)
        
        val BASE_URL = "http://15.164.153.174"
        
//        로그인 기능 => 함수 작성
        
        fun postRequestLogin(id : String, pw : String) {
            
//            안드로이드 앱이 클라이언트로 동작하도록 도와주자
            val client = OkHttpClient()
            
//            어느 기능으로 갈건지 주소 완성
            val urlStr = "${BASE_URL}/user"
            
//            파라미터들을 담아주자 => POST(PUT/PATCH) - formData 활용 
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()
            
//            목적지의 정보를 Request 형태로 완성하자 (티켓 최종 발권)
            
            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .build()
            
//            미리 만들어둔 client 변수를 활용해서 request 변수에 적힌 정보로 서버에 요청 날리기
            
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결 자체에 실패
                }

                override fun onResponse(call: Call, response: Response) {
//                    결과가 성공이던 / 실패던 상관없이, 서버가 뭔가 답변을 해준 경우
//                    응답이 돌아온 경우
                }
                
            })
            
        }

    }
}
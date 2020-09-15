package kr.co.tjoeun.daily10minutes_20200824

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        참여중인 사람 리스트 보기
        viewAllMembersBtn.setOnClickListener {
            


        }

//        신청하기 버튼을 누르면 => 정말 신청할건지? 확인(AlertDialog)하고-> 확인되면 신청처리
        applyBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("프로젝트 참가 확인")
//            메세지 : ex. 독서하기 프로젝트에 참가하시겠습니까?
            alert.setMessage("${mProject.title} 프로젝트에 참가하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
//                실제로 서버에 신청처리
                ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
                    override fun onResponse(json: JSONObject) {
//                        자동 새로고침이 구현은 되지만 -> 서버를 한더 요청하기 때문에
//                        신청 결과에서 알려주는 데이터를 화면에 반영
//                        getProjectDetailFromServer()

                        val code = json.getInt("code")

                        if (code == 200) {
                            val data = json.getJSONObject("data")
                            val projectObj = data.getJSONObject("project")

                            mProject = Project.getProjectFromJson(projectObj)

                            runOnUiThread {
                                Toast.makeText(mContext, "프로젝트 참가 신청완료", Toast.LENGTH_SHORT).show()

//                                인원수 / 참가 버튼 등 UI 변경
                                refreshProjectDataUI()
                            }
                        }

                    }

                })
            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }
    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)

        titleTxt.text = mProject.title
        desctiponTxt.text = mProject.description
    }

    override fun onResume() {
        super.onResume()
        getProjectDetailFromServer()
    }

    fun getProjectDetailFromServer(){

        ServerUtil.getRequestProjectDetailById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

                mProject = Project.getProjectFromJson(projectObj)

                runOnUiThread {
                    refreshProjectDataUI()
                }

            }
        })

    }

//    서버에서 준 프로젝트 정보 (내용이 변경된 mProject)를 => UI에 새로 반영하는 기능
    fun refreshProjectDataUI(){

        proofMethodTxt.text = mProject.proofMethod
        onGoingMemberCountTxt.text = "(현재 참여 인원 : ${mProject.onGoingMemberCount}명"

    //                    myLastStatus =>  마지막으로 변경된 프로젝트 신청상태
    //                    NULL : 신청한적이 없다.
    //                    ONGOING : 신청해서 진행중
    //                    FAIL : 중도포기, 3일 연속 인증글 X (자동 포기)
    //                    COMPLETE : 66일 짜리 모두 수행 완료 (프로젝트마다 다름)

    //                    신청하기 언제? 그 외의 모든 상황
    //                    중도 포기 언제? 상태가 ONGING일때

        if (mProject.myLastStatus == "ONGOING") {
            giveUpBtn.isEnabled = true
            applyBtn.isEnabled = false
        } else {
            giveUpBtn.isEnabled = false
            applyBtn.isEnabled = true

        }
    }

}
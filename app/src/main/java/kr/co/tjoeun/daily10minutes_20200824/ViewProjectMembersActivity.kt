package kr.co.tjoeun.daily10minutes_20200824

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.datas.User
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class ViewProjectMembersActivity : BaseActivity() {

//    프로젝트 상세화면에서 넘겨준, 프로젝트 데이터를 담기 위한 멤버변수
    lateinit var mProject : Project

    val mProjectMembers = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_members)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

        getProjectMembersFromServer()

    }

//    서버에서 프로젝트의 참여 멤버 불러오는 기능
    fun getProjectMembersFromServer(){

        ServerUtil.getRequestProjectMemberById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

                val ongoingUserArr = projectObj.getJSONArray("ongoing_users")

                for (i in 0 until ongoingUserArr.length()){

                    val memberObj = ongoingUserArr.getJSONObject(i)

//                    memberObj => User 형태로 변환 => ArrayList에 추가


                }

            }
        })

    }
}
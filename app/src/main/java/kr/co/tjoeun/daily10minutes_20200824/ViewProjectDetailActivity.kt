package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    override fun setupEvents() {
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


            }
        })

    }
}
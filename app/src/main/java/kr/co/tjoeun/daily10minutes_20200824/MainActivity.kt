package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectAdapter
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList<Project>()

    lateinit var mProjectAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectList)
        projectListView.adapter = mProjectAdapter

    }

    fun getProjectListFromServer() {
        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectArr = dataObj.getJSONArray("projects")

                for (i in 0 until projectArr.length()){
                    val projectObj = projectArr.getJSONObject(i)

                    val project = Project()
                    project.id = projectObj.getInt("id")
                    project.title = projectObj.getString("title")
                    project.imageUrl = projectObj.getString("img_url")
                    project.description = projectObj.getString("description")

                    mProjectList.add(project)
                }

//                비동기처리 => 어댑터 연결이 먼저 끝나고, 프로젝트목록이 나중에 추가될수 있다.
//                리스트뷰의 입장에서는 => 내용물 목록이 변경된 상황 => notifyDataSetChanged()로 새로고침
//                새로고침 : UI에 영향을 주겠다. => runOnUiThred{ }
                runOnUiThread {
                    mProjectAdapter.notifyDataSetChanged()
                }

            }
        })
    }

}
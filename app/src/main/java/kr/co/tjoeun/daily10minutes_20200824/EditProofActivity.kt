package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_proof.*
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class EditProofActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_proof)

        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        postProofBtn.setOnClickListener {

            val inputContent = proofContentEdt.text.toString()

            ServerUtil.postRequestWriteProof(mContext, mProject.id, inputContent, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                }
            })
        }
    }

    override fun setValues() {
        mProject = intent.getSerializableExtra("project") as Project


    }
}
package kr.co.tjoeun.daily10minutes_20200824

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_project_detail.*

class ViewProjectMembersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_members)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        viewAllMembersBtn.setOnClickListener {

            val myIntent = Intent(mContext, ViewProjectMembersActivity::class.java)
        }
    }

    override fun setValues() {
    }
}
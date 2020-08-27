package kr.co.tjoeun.daily10minutes_20200824.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200824.R
import kr.co.tjoeun.daily10minutes_20200824.datas.Project

class ProjectAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<Project>
) : ArrayAdapter<Project>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.project_list_item, null)
        }

        val row = tempRow!!

        val img = row.findViewById<ImageView>(R.id.img)
        val titleTxt = row.findViewById<TextView>(R.id.titleTxt)
        val desctiponTxt = row.findViewById<TextView>(R.id.desctiponTxt)

        val data = mList[position]

        //img = data.imageUrl
        Glide.with(mContext).load(data.imageUrl).into(img)
        titleTxt.text = data.title
        desctiponTxt.text = data.description

        return row

    }
}
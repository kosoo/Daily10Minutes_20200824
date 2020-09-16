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
import kr.co.tjoeun.daily10minutes_20200824.datas.Proof
import kr.co.tjoeun.daily10minutes_20200824.datas.User

class ProofAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<Proof>
) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val proofContentTxt = row.findViewById<TextView>(R.id.proofContentTxt)
        val proofFirstImg = row.findViewById<ImageView>(R.id.proofFirstImg)

        val data = mList[position]

        proofContentTxt.text = data.content
        //Glide.with(mContext).load()


        /*val userFirstProfileImg = row.findViewById<ImageView>(R.id.userFirstProfileImg)
        val userNickNameTxt = row.findViewById<TextView>(R.id.userNickNameTxt)
        val userEmailTxt = row.findViewById<TextView>(R.id.userEmailTxt)

        val user = mList[position]

        userNickNameTxt.text = user.nickName
        userEmailTxt.text = user.email

        Glide.with(mContext).load(user.profileImagesArrayList[0]).into(userFirstProfileImg)*/



        return row

    }
}
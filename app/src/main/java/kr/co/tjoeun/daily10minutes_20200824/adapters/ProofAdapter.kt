package kr.co.tjoeun.daily10minutes_20200824.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
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
        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)

        val data = mList[position]

        proofContentTxt.text = data.content
//       첨부된 이미지뷰가 있다면, 이미지뷰를 화면에 표시, 인증글에 사진이 없다면 이미지뷰를 숨김처리
        if (data.imgList.size == 0){
            proofFirstImg.visibility = View.GONE //숨김처리
        }else{
            proofFirstImg.visibility = View.VISIBLE
            Glide.with(mContext).load(data.imgList[0]).into(proofFirstImg)
        }

//        작성자 프사 / 닉네임 변경

        Glide.with(mContext).load(data.writer.profileImagesArrayList[0]).into(writerProfileImg)
        writerNickNameTxt.text = data.writer.nickName

//        좋아요 / 댓글 갯수 반영
        likeBtn.text = "좋아요 : ${data.likeCount}개"
        replyBtn.text = "댓글 : ${data.replyCount}개"

        return row
    }
}
package com.appleobject.leaderboard.ui.fragments

import android.app.DownloadManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appleobject.leaderboard.R
import com.appleobject.leaderboard.retrofit.Skilliq
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.top_skill_item_view.view.*

class SkillAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var skillList: List<Skilliq> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_skill_item_view,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SkillHolder -> {
                holder.bind(skillList[position])
            }
        }
    }

    override fun getItemCount(): Int = skillList.size


    class SkillHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val skillName: TextView = itemView.skillName
        private val skillScore: TextView = itemView.skillIqScore
        private val skillCountry: TextView = itemView.skillCountry
        private val skillImage: ImageView = itemView.tvSkillUrl

        fun bind(skilliq: Skilliq){
            skillName.text = skilliq.name
            skillScore.text = skilliq.score.toString()
            skillCountry.text = skilliq.country

            val requestOption = RequestOptions()
                .placeholder(R.drawable.ic_badge_top_learner)
                .error(R.drawable.ic_badge_top_learner)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOption)
                .load(skilliq.badgeUrl)
                .into(skillImage)

        }

    }

    fun submitSkillList(skilliqList: List<Skilliq>){
        skillList = skilliqList
    }
}
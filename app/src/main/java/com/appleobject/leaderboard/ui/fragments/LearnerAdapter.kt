package com.appleobject.leaderboard.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appleobject.leaderboard.R
import com.appleobject.leaderboard.model.Learners
import com.appleobject.leaderboard.model.SkillsRec
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.top_learner_item_view.view.*
import kotlinx.android.synthetic.main.top_skill_item_view.view.*

class LearnerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var learners : List<Learners> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LearnerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_learner_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LearnerViewHolder ->{
                holder.bind(learners[position])
            }

        }
    }

    override fun getItemCount(): Int = learners.size

    fun submitList(learnerList: List<Learners>){
        learners = learnerList
        notifyDataSetChanged()
    }

    class LearnerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val learnerName: TextView = itemView.learnerName
        private val learnHour: TextView = itemView.learnerHour
        private val countryName: TextView = itemView.learnerCountry
        private val imageUrl: ImageView = itemView.tvBadgeUrl


        fun bind(learners: Learners){

            learnerName.text =learners.name
            learnHour.text = learners.hours.toString()
            countryName.text = learners.country

            val requestOption = RequestOptions()
                .placeholder(R.drawable.ic_hours_badge)
                .error(R.drawable.ic_hours_badge)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOption)
                .load(learners.badgeUrl)
                .into(imageUrl)


        }



    }

}
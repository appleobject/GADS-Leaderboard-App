package com.appleobject.leaderboard

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.appleobject.leaderboard.helper.toast
import com.appleobject.leaderboard.model.Learners
import com.appleobject.leaderboard.retrofit.LearnersRetrofit
import com.appleobject.leaderboard.retrofit.ServiceBuilder
import com.appleobject.leaderboard.retrofit.Skilliq
import com.appleobject.leaderboard.ui.ProjectSubmissionActivity
import com.appleobject.leaderboard.ui.fragments.PageAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


        val pageAdapter = PageAdapter(this)
        viewPager.adapter = pageAdapter



        TabLayoutMediator(tabLayout, viewPager, true)
        { tab, position ->
            tab.text = pageAdapter.getPageTitle(position)
        }.attach()


    }

    private fun handleSubmitAction() {
        startActivity(Intent(this, ProjectSubmissionActivity::class.java))
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.submit_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_submit -> {
                handleSubmitAction()

            }
        }

        return super.onOptionsItemSelected(item)
    }
}





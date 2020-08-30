package com.appleobject.leaderboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appleobject.leaderboard.helper.toast
import com.appleobject.leaderboard.model.Learners
import com.appleobject.leaderboard.retrofit.LearnersRetrofit
import com.appleobject.leaderboard.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadHours()
    }

    private fun loadHours() {
        tvHello.setOnClickListener {
            val getLearners = ServiceBuilder.buildService(LearnersRetrofit::class.java)
            val requestCall = getLearners.get()

            requestCall.enqueue(object :Callback<List<Learners>>{
                override fun onResponse(
                    call: Call<List<Learners>>,
                    response: Response<List<Learners>>
                ) {
                    if (response.isSuccessful){
                        val response = response.body()
                        tvHello.text = response.toString()
                    }
                }

                override fun onFailure(call: Call<List<Learners>>, t: Throwable) {
                    toast("Error occurred : $t")
                }

            })
        }
    }
}
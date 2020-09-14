package com.appleobject.leaderboard.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appleobject.leaderboard.R
import com.appleobject.leaderboard.model.Learners
import com.appleobject.leaderboard.retrofit.LearnersRetrofit
import com.appleobject.leaderboard.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_learning_leaders.*
import kotlinx.android.synthetic.main.fragment_learning_leaders.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LearningLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearningLeadersFragment : Fragment() {
    private val TAG = "LearningLeadersFragment"
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var learnAdapter: LearnerAdapter
    //private lateinit var recyclerLearner: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_learning_leaders, container, false)





        Log.d(TAG, "onCreateView: is called")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadHours()
        Log.d(TAG, "onViewCreated: is called...")
    }

    private fun initRecyclerView() {
        val leaderRecycler = view?.findViewById<RecyclerView>(R.id.recyclerLearner)
        learnAdapter = LearnerAdapter()
        leaderRecycler?.adapter = learnAdapter
        leaderRecycler?.layoutManager = LinearLayoutManager(context)


    }

    private fun loadHours() {
        val learners = ServiceBuilder.buildService(LearnersRetrofit::class.java)
        val requestCall = learners.getLearnersHours()

        requestCall.enqueue(object : Callback<List<Learners>> {
            override fun onResponse(
                call: Call<List<Learners>>,
                response: Response<List<Learners>>
            ) {
                if (response.isSuccessful) {
                    val learnerResponse = response.body()
                    learnAdapter.submitList(learnerResponse!!)

                }
            }

            override fun onFailure(call: Call<List<Learners>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LearningLeadersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LearningLeadersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
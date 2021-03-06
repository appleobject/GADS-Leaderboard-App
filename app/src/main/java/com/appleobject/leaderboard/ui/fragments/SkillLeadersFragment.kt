package com.appleobject.leaderboard.ui.fragments

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
import com.appleobject.leaderboard.retrofit.LearnersRetrofit
import com.appleobject.leaderboard.retrofit.ServiceBuilder
import com.appleobject.leaderboard.retrofit.Skilliq
import kotlinx.android.synthetic.main.fragment_skill_leaders.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SkillLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillLeadersFragment : Fragment() {

    private val TAG = "SkillLeadersFragment"
    lateinit var skillAdapter: SkillAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
      val view = inflater.inflate(R.layout.fragment_skill_leaders, container, false)

        Log.d(TAG, "onCreateView: is called...")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSkillRecycler()
        passData()
        Log.d(TAG, "onViewCreated: is called...")

    }

    private fun passData() {
        val builder = ServiceBuilder.buildService(LearnersRetrofit::class.java)
        val requestCall = builder.getLearnersSkills()

        requestCall.enqueue(object : Callback<List<Skilliq>> {
            override fun onResponse(call: Call<List<Skilliq>>, response: Response<List<Skilliq>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    skillAdapter.submitSkillList(responseBody!!)
                }
            }

            override fun onFailure(call: Call<List<Skilliq>>, t: Throwable) {
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    private fun initSkillRecycler() {

        val skillRecycler = view?.findViewById<RecyclerView>(R.id.recyclerSkills)
        skillAdapter = SkillAdapter()
        skillRecycler?.adapter = skillAdapter
        skillRecycler?.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SkillLeadersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SkillLeadersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
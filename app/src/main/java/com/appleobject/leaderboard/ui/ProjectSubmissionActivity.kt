package com.appleobject.leaderboard.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.appleobject.leaderboard.MainActivity
import com.appleobject.leaderboard.PROJECT_SUBMISSION
import com.appleobject.leaderboard.R
import com.appleobject.leaderboard.retrofit.LearnersRetrofit
import com.appleobject.leaderboard.retrofit.SubmissionBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_project_submission.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmissionActivity : AppCompatActivity() {

    private val TAG = "ProjectSubmissionActivity"


    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)

        context = this

        setSupportActionBar(toolbar2)
        toolbar2.title = PROJECT_SUBMISSION
        toolbar2.setNavigationIcon(R.drawable.ic_keyboard_backspace_24)


        btnSubmit.setOnClickListener {
            handleSubmitButton()
            Log.d(TAG, "onCreate: Submit button clicked")
        }

        toolbar2.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            Log.d(TAG, "onCreate: toolbar backspace icon clicked")

        }

    }

    private fun submitForm(context: Context) {
        val firstName = firstNameField.text.toString()
        val lastName = lastNameField.text.toString()
        val emailAdd = emailAddressField.text.toString()
        val githubLink = githubLinkField.text.toString()
        val submitProject = SubmissionBuilder.buildSubmission(LearnersRetrofit::class.java)
        val requestCall = submitProject.submitApp(firstName,lastName,emailAdd,githubLink)

        if (
            firstNameField.text.isNotEmpty() && lastNameField.text.isNotEmpty() &&
            emailAddressField.text.isNotEmpty() && githubLinkField.text.isNotEmpty()
        ){
            requestCall.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val alertDialog = AlertDialog.Builder(context)
                        val customSuccess = layoutInflater.inflate(R.layout.custom_dialog_success, null)
                        alertDialog.setView(customSuccess)
                        alertDialog.show()
                        Log.d(TAG, "onResponse: ${responseBody.toString()}")

                    }
                }


                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message.toString()}")
                    val alertDialog = AlertDialog.Builder(context)
                    val customError = layoutInflater.inflate(R.layout.custom_dialog_error, null)
                    alertDialog.setView(customError)
                    alertDialog.show()

                }

            })
            firstNameField.setText("")
            lastNameField.setText("")
            emailAddressField.setText("")
            githubLinkField.setText("")

        }else{
            Snackbar.make(root_layout,"You can't submit an empty fields",Snackbar.LENGTH_LONG).show()
            Log.d(TAG, "submitForm: Running method")
        }



    }

    private fun handleSubmitButton() {
        val dialog = AlertDialog.Builder(context)
        val customCheck = layoutInflater.inflate(R.layout.check_dialog,null)
        val btnYes = customCheck.findViewById<Button>(R.id.btn_yes)
        val iconCancel = customCheck.findViewById<ImageView>(R.id.icon_cancel)
        dialog.setView(customCheck)
        dialog.setCancelable(false)

        val customDialog = dialog.create()

        btnYes.setOnClickListener {
            submitForm(it.context)
            Log.d(TAG, "handleSubmitButton: btn_yes was clicked...")
            customDialog.dismiss()
        }

        iconCancel.setOnClickListener {
            Log.d(TAG, "handleSubmitButton: iconCancel was clicked...")
            customDialog.dismiss()

        }

        customDialog.show()




    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
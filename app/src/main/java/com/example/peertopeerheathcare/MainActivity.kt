package com.example.peertopeerheathcare
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Constants.isIntiatedNow = true
        Constants.isCallEnded = true
        start_meeting.setOnClickListener {
            if (meeting_id.text.toString().trim().isNullOrEmpty())
                meeting_id.error = "Please enter meeting id"
            else {
                Log.d("SHIVAM", "${meeting_id.text.toString()}")
                db.collection("calls")
                    .document(meeting_id.text.toString())
                    .get()
                    .addOnSuccessListener {
                        if (it["type"] == "OFFER" || it["type"] == "ANSWER" || it["type"] == "END_CALL") {
                            meeting_id.error = "Please enter new meeting ID"
                        } else {
                            val intent = Intent(this@MainActivity, InternalUIActivity::class.java)
                            intent.putExtra("meetingID", meeting_id.text.toString())
                            intent.putExtra("isJoin", false)
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener {
                        meeting_id.error = "Please enter new meeting ID"
                    }
            }
        }
        join_meeting.setOnClickListener {
            Log.d("SHIVAM", "${meeting_id.text.toString()}")
            if (meeting_id.text.toString().trim().isNullOrEmpty())
                meeting_id.error = "Please enter meeting id"
            else {
                val intent = Intent(this@MainActivity, InternalUIActivity::class.java)
                intent.putExtra("meetingID", meeting_id.text.toString())
                intent.putExtra("isJoin", true)
                startActivity(intent)
            }
        }
    }
}

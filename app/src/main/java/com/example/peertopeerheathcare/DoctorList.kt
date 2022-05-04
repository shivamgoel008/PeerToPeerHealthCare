package com.example.peertopeerheathcare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peertopeerheathcare.Activity.MainActivity
import kotlinx.android.synthetic.main.activity_doctor_list.*

class DoctorList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        doc_name.setOnClickListener {
            val intent = Intent(this@DoctorList, MainActivity::class.java)
            startActivity(intent)
        }

        doc_name2.setOnClickListener {
            val intent = Intent(this@DoctorList, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
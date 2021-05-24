package com.example.textreco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome_page.*

class WelcomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        supportActionBar?.setTitle("RECONO")
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setIcon(R.drawable.search)
        txtDetectBtn.setOnClickListener{
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }
        objDetectBtn.setOnClickListener{
            val intent2 = Intent(this, ObjectDetectionActivity::class.java)
            startActivity(intent2)
        }
    }
}
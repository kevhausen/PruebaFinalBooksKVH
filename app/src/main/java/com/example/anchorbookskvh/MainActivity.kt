package com.example.anchorbookskvh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anchorbookskvh.views.BookListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frame_main,BookListFragment()).commit()
    }
}
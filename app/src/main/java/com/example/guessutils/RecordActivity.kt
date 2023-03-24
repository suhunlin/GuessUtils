package com.example.guessutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guessutils.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordBinding
    var tag:String = RecordActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
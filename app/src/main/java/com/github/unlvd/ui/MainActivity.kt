package com.github.unlvd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.unlvd.R
import com.github.unlvd.databinding.ActivityMainBinding
import com.github.unlvd.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    binding = ActivityMainBinding.inflate(layoutInflater)
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    if(savedInstanceState == null){
      supportFragmentManager.beginTransaction()
        .add(R.id.container, MainFragment())
        .commitAllowingStateLoss()
    }
  }
}
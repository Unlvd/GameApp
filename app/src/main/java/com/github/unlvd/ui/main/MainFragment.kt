package com.github.unlvd.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.unlvd.R
import com.github.unlvd.databinding.FragmentMainBinding
import com.github.unlvd.ui.base.viewBinding

class MainFragment: Fragment(R.layout.fragment_main) {

  private val binding by viewBinding { FragmentMainBinding.bind(it) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

}
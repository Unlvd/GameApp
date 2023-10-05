package com.github.unlvd.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.unlvd.R
import com.github.unlvd.databinding.FragmentMainBinding
import com.github.unlvd.ui.base.viewBinding
import com.github.unlvd.viewmodel.main.MainScreenViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

  private val binding by viewBinding { FragmentMainBinding.bind(it) }
  private val viewModel by viewModels<MainScreenViewModel>()

  private val adapter = ListDelegationAdapter(
    MainScreenDelegates.gamesHorizontalDelegate
  )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    with(binding) {
      recyclerView.adapter = adapter
      viewModel.data.observe(viewLifecycleOwner, Observer {
        adapter.apply {
          items = it
          notifyDataSetChanged()
        }
      })

    }
  }

}
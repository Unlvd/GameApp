package com.github.unlvd.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.unlvd.R
import com.github.unlvd.databinding.FragmentMainBinding
import com.github.unlvd.ui.base.ListItem
import com.github.unlvd.ui.base.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainFragment: Fragment(R.layout.fragment_main) {

  private val binding by viewBinding { FragmentMainBinding.bind(it) }

  private val adapter = ListDelegationAdapter(
    MainScreenDelegates.gamesHorizontalDelegate
  )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    with(binding){
      recyclerView.adapter = adapter

      adapter.apply {
        items = listOf(GamesHorizontalItem(listOf
          (GameWideItem(0,"game1"),
          GameWideItem(1,"game2")
            )))
        notifyDataSetChanged()
      }
    }
  }

}
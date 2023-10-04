package com.github.unlvd.ui.main

import com.github.unlvd.databinding.ItemGameWideBinding
import com.github.unlvd.databinding.ItemGamesHorizontalBinding
import com.github.unlvd.ui.base.ListItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenDelegates {



  val gamesHorizontalDelegate =
    adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
      { inflater, container ->
        ItemGamesHorizontalBinding.inflate(inflater, container, false).apply {
          recyclerView.adapter = horizontalGameAdapter
        }
      }
    ) {
      bind {(binding.recyclerView.adapter as ListDelegationAdapter<List<ListItem>>).apply {
        items = item.games
        notifyDataSetChanged()
      } }
      }

  private val wideGameDelegate =
    adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
    {inflater , container -> ItemGameWideBinding.inflate(inflater,container,false) }
    ){
      bind{
        binding.title = item.title
        binding.executePendingBindings()
      }
    }
  private val horizontalGameAdapter = ListDelegationAdapter(
    wideGameDelegate
  )
}

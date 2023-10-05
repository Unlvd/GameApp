package com.github.unlvd.ui.main

import com.github.unlvd.databinding.ItemGameThinBinding
import com.github.unlvd.databinding.ItemGameWideBinding
import com.github.unlvd.databinding.ItemGamesHorizontalBinding
import com.github.unlvd.databinding.ItemProgressThinBinding
import com.github.unlvd.databinding.ItemProgressWideBinding
import com.github.unlvd.model.base.ListItem
import com.github.unlvd.model.game.GameThinItem
import com.github.unlvd.model.game.GameWideItem
import com.github.unlvd.model.game.GamesHorizontalItem
import com.github.unlvd.model.game.ProgressThinItem
import com.github.unlvd.model.game.ProgressWideItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenDelegates {


  val gamesHorizontalDelegate =
    adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
      { inflater, container -> ItemGamesHorizontalBinding.inflate(inflater, container, false) }
    ) {
      binding.recyclerView.adapter = ListDelegationAdapter(
        wideGameDelegate,
        thinGameDelegate,
        wideProgressDelegate,
        thinProgressDelegate
      )
      bind {
        binding.titleTextView.text = item.title
        (binding.recyclerView.adapter as ListDelegationAdapter<List<ListItem>>)
          .apply {
            items = item.games
            notifyDataSetChanged()
          }
      }
    }

  private val wideGameDelegate =
    adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
      { inflater, container -> ItemGameWideBinding.inflate(inflater, container, false) }
    ) {
      bind {
        binding.imageView.setBackgroundColor(item.hashCode())
        binding.title = item.title
        binding.executePendingBindings()
      }
    }

  private val thinGameDelegate =
    adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
      { inflater, container -> ItemGameThinBinding.inflate(inflater, container, false) }
    ) {
      bind {
        binding.imageView.setBackgroundColor(item.hashCode())
        binding.title = item.title
        binding.executePendingBindings()
      }
    }

  private val thinProgressDelegate =
    adapterDelegateViewBinding<ProgressThinItem, ListItem, ItemProgressThinBinding>(
      { inflater, container -> ItemProgressThinBinding.inflate(inflater, container, false) }
    ) {
      bind {
      }
    }

  private val wideProgressDelegate =
    adapterDelegateViewBinding<ProgressWideItem, ListItem, ItemProgressWideBinding>(
      { inflater, container -> ItemProgressWideBinding.inflate(inflater, container, false) }
    ) {
      bind {
      }
    }

}

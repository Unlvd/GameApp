package com.github.unlvd.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.unlvd.model.base.ListItem
import com.github.unlvd.model.game.GameThinItem
import com.github.unlvd.model.game.GameWideItem
import com.github.unlvd.model.game.GamesHorizontalItem
import com.github.unlvd.model.game.ProgressThinItem
import com.github.unlvd.model.game.ProgressWideItem
import com.github.unlvd.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel: BaseViewModel() {


  private var _data =  MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data
  init {

    viewModelScope.launch(Dispatchers.IO) {
      _data.postValue(getLoaders())
      val items = getItems()
      _data.postValue(items)
    }
  }
  private suspend fun getItems():List<ListItem>{
    delay(1500)
    return listOf(
      GamesHorizontalItem(
        title = "Wide title",
        games = (0..20).map { GameWideItem(it.toLong(), "Game $it") }),
      GamesHorizontalItem(
        title = "Thin title",
        games = (0..20).map { GameThinItem(it.toLong(), "Game $it") }),
      GamesHorizontalItem(
        title = "Wide title",
        games = (0..20).map { GameWideItem(it.toLong(), "Game $it") })
    )
  }
  private fun getLoaders(): List<ListItem>{
    return  listOf(
      GamesHorizontalItem(
        title = "Wide title",
        games = (0..3).map { ProgressWideItem }),
      GamesHorizontalItem(
        title = "Thin title",
        games = (0..5).map { ProgressThinItem }),
      GamesHorizontalItem(
        title = "Wide title",
        games = (0..3).map { ProgressWideItem })
    )
  }
}
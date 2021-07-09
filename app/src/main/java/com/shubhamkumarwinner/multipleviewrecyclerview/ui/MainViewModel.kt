package com.shubhamkumarwinner.multipleviewrecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhamkumarwinner.multipleviewrecyclerview.data.network.Resource
import com.shubhamkumarwinner.multipleviewrecyclerview.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _homeListItemsLiveData = MutableLiveData<Resource<List<HomeRecyclerViewItem>>>()
    val homeListItemsLiveData: LiveData<Resource<List<HomeRecyclerViewItem>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    private fun getHomeListItems() = viewModelScope.launch {
        _homeListItemsLiveData.postValue(Resource.Loading)
        val moviesDiffered = async { repository.getMovies() }
        val directorsDiffered = async { repository.getDirectors() }

        val movies = moviesDiffered.await()
        val directors = directorsDiffered.await()

        val homeItemsList = mutableListOf<HomeRecyclerViewItem>()
        if (movies is Resource.Success && directors is Resource.Success){
            homeItemsList.add(HomeRecyclerViewItem.Title(1, "Recommended Movies"))
            homeItemsList.addAll(movies.value)
            homeItemsList.add(HomeRecyclerViewItem.Title(2, "Top Directors"))
            homeItemsList.addAll(directors.value)
            _homeListItemsLiveData.postValue(Resource.Success(homeItemsList))
        }else{
            Resource.Failure(false, null, null)
        }
    }
}
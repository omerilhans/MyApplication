package com.omerilhanli.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.omerilhanli.myapplication.data.Resource
import com.omerilhanli.myapplication.data.datasource.FetchError
import com.omerilhanli.myapplication.data.datasource.FetchResponse
import com.omerilhanli.myapplication.data.repository.DataSourceRepository
import com.omerilhanli.myapplication.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(
    private val dataSourceRepository: DataSourceRepository,
) : BaseViewModel() {

    val peopleResponse = MutableLiveData<Resource<*>>()
    var isDataCouldNotRetrieved = MutableLiveData<Boolean>()
    var swipedForRefresh: Boolean = false
    val next = MutableLiveData("1")

    fun getPeople() {
        loading.postValue(true)

        viewModelScope.launch {
            dataSourceRepository
                .fetchPeople(next.value) { fetchResponse: FetchResponse?, fetchError: FetchError? ->
                    loading.postValue(false)

                    fetchResponse?.let {
                        it.next?.let { nextPageNumber ->
                            next.postValue(nextPageNumber)
                        }

                        peopleResponse.postValue(Resource.success(it))
                        isDataCouldNotRetrieved.postValue(it.people.isEmpty())
                    }

                    fetchError?.let {
                        if (it.errorDescription.isNotEmpty()) {
                            peopleResponse.postValue(Resource.error(it))
                        }
                        isDataCouldNotRetrieved.postValue(true)
                    }
                }
        }
    }

}
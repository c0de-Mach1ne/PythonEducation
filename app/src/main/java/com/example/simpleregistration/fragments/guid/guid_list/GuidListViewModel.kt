package com.example.simpleregistration.fragments.guid.guid_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.Loading

class GuidListViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _guidList = MutableLiveData<List<Guid>>(listOf())
    var guidList: LiveData<List<Guid>> = _guidList

    private val _uiState = MutableLiveData<Loading>()
    var uiState: LiveData<Loading> = _uiState

    fun getGuidList() {
        _uiState.value = Loading.Start
        dataRepository.getGuid().addOnCompleteListener { data ->
            val guidList = mutableListOf<Guid>()
            for (guid in data.result.children) {
                guid.getValue(Guid::class.java)?.let { guidList.add(it) }
            }
            _guidList.postValue(guidList)
            if (data.isComplete) _uiState.value = Loading.Stop
        }.addOnFailureListener {
            _uiState.value = Loading.Error(it.message.toString())
        }
    }
}


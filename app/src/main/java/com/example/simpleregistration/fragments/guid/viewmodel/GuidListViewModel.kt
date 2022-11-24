package com.example.simpleregistration.fragments.guid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.fragments.model.repository.DataRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class GuidListViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _guidList = MutableLiveData<List<Guid>>(listOf())
    var guidList: LiveData<List<Guid>> = _guidList

    fun getList() {
        dataRepository.getGuidRef().addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val guidList = mutableListOf<Guid>()
                    Log.d("TAG", "Start")
                    for (guidId in 0 until snapshot.childrenCount.toInt()) {
                        dataRepository.getGuidById(guidId).addOnCompleteListener {
                            it.result.getValue(Guid::class.java)?.let { data -> guidList.add(data) }
                            _guidList.postValue(guidList)
                            if(guidList.size == snapshot.childrenCount.toInt()){
                                Log.d("TAG", "точно End")
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // TODO здесь в этом методе мне нужно что-то передавать ?
                }
            }
        )
    }

}
package com.example.simpleregistration.fragments.guid

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.fragments.model.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class GuidViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _guidList = MutableLiveData<List<Guid>>(listOf())
    var guidList: LiveData<List<Guid>> = _guidList

    fun getList() {
        userRepository.getRef().addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val guidList = mutableListOf<Guid>()
                    for (i in 0 until snapshot.childrenCount.toInt()) {
                        userRepository.test(i).addOnCompleteListener {
                            it.result.getValue(Guid::class.java)?.let { data -> guidList.add(data) }
                            _guidList.postValue(guidList)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
//                    Toast.makeText(, error.message, Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

}
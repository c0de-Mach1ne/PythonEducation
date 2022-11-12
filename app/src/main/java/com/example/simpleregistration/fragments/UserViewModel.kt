package com.example.simpleregistration.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.repository.AuthRepositoryImpl

class UserViewModel(private val authRepositoryImpl: AuthRepositoryImpl): ViewModel() {

    private val _userData = MutableLiveData<UserPersonalInfo>()
    var userData: LiveData<UserPersonalInfo> = _userData

    fun getUserData(){
        authRepositoryImpl.getDatabaseUser()?.addOnCompleteListener { dataSnapshotTask ->
            val result = dataSnapshotTask.result.getValue(UserPersonalInfo::class.java)
            _userData.value = UserPersonalInfo(
                name = result?.name,
                sureName = result?.sureName,
                patronymic = result?.patronymic,
                teacherFlag = result?.teacherFlag
            )
        }
    }

    fun singOut() =
        authRepositoryImpl.signOut()
}
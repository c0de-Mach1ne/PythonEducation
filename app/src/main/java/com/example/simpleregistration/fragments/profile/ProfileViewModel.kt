package com.example.simpleregistration.fragments.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.repository.AuthRepository

class ProfileViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val _userData = MutableLiveData<UserPersonalInfo>()
    var userData: LiveData<UserPersonalInfo> = _userData

    init {
        getUserData()
    }

    private fun getUserData(){
        authRepository.getDatabaseUser()?.addOnCompleteListener { dataSnapshotTask ->
            Log.d("TAG", "userData ${dataSnapshotTask.result}")
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
        authRepository.signOut()
}
package com.example.simpleregistration.fragments.quiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.repository.DataRepository
import com.example.simpleregistration.fragments.model.Quiz
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class QuizListViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _quizList = MutableLiveData<List<Quiz>>()
    var quizList: LiveData<List<Quiz>> = _quizList

    fun getQuizList() {
        dataRepository.getQuizRef().addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val listOfQuiz = mutableListOf<Quiz>()
                    for (quizId in 0 until snapshot.childrenCount.toInt()) {
                        dataRepository.getQuizById(quizId).addOnCompleteListener {
                            it.result.getValue(Quiz::class.java)
                                ?.let { data -> listOfQuiz.add(data) }
                            _quizList.postValue(listOfQuiz)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}
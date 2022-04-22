package com.example.physicswallah.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.physicswallah.model.UsersResponse
import com.example.physicswallah.repo.AuthRepository
import com.example.physicswallah.utils.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class GetTeachersViewModel @Inject constructor
        ( val authRepository: AuthRepository) : ViewModel() {
        val _loadingState = MutableLiveData<LoadingState>()
        val userDetailsLiveData = MutableLiveData<UsersResponse>()
        val loadingState: LiveData<LoadingState>
            get() = _loadingState


        fun getTeachersInfo() {
            _loadingState.value = LoadingState.LOADING
            authRepository.getUserInfo({
                userDetailsLiveData.postValue(it)
                _loadingState.value = LoadingState.LOADED
            }, {
                _loadingState.value = LoadingState.error(it)
            })
        }
}
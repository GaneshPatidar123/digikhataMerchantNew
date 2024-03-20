package com.merchant.digikhatamerchant.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digikhata.merchant.Utils.Resource
import com.example.authdemo.models.response.auth.SignUpResponse
import com.merchant.digikhatamerchant.Model.Request.ChangePasswordRequest


import com.merchant.digikhatamerchant.Repo.ChangePasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val changePasswordRepository: ChangePasswordRepository,
): ViewModel(){
    private val _dataState: MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()

    val dataState: LiveData<Resource<SignUpResponse>>
        get() = _dataState

    fun changePassword(
        getChangePasswordStateEvent: GetChangePasswordStateEvent,
        request: ChangePasswordRequest,
        accessToken: String?
    ) {
        _dataState.value = Resource.Loading
        viewModelScope.launch {
            when (getChangePasswordStateEvent) {
                is GetChangePasswordStateEvent.changePasswordState -> {
                    changePasswordRepository.callChangePassResponse(request,accessToken)
                        .collect { dataState ->
                            _dataState.value = dataState
                        }
                }
                else -> {}
            }
        }
    }

}

sealed class GetChangePasswordStateEvent {
    object changePasswordState : GetChangePasswordStateEvent()
}

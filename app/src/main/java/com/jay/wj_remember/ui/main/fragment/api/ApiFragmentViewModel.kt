package com.jay.wj_remember.ui.main.fragment.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.LocalUseCase
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiFragmentViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
) : BaseViewModel() {

    private val _apiUserList = MutableLiveData<List<User>>()
    val apiUserList: LiveData<List<User>>
        get() = _apiUserList

    fun setApiUserList(userList: List<User>) {
        _apiUserList.value = userList
    }

    fun apiclick() {

    }

//    fun call() {
//        val list = mutableListOf<User>().apply {
//            for (i in 0..99) {
//                val user: User = if (i % 15 == 0) {
//                    User(
//                        name = "api user: $i",
//                        image = null,
//                        hasLiked = true,
//                        header = true,
//                        id = i.toLong()
//                    )
//                } else {
//                    User(
//                        name = "api user: $i",
//                        image = null,
//                        hasLiked = false,
//                        header = false,
//                        id = i.toLong()
//                    )
//                }
//
//                add(user)
//            }
//        }
//
//        _apiUserList.value = list
//    }

}
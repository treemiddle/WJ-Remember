package com.jay.wj_remember.ui.main.fragment.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.domain.usecase.LocalUseCase
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalFragmentViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
) : BaseViewModel() {

    private val _localUserList = MutableLiveData<List<User>>()
    val localUserList: LiveData<List<User>>
        get() = _localUserList

    fun setLocalUserList(userList: List<User>) {
        _localUserList.value = userList
    }

    fun localclick() {

    }

//    fun call() {
//        val list = mutableListOf<User>().apply {
//            for (i in 0..10) {
//                val user: User = if (i % 5 == 0) {
//                    User(
//                        name = "local user: $i",
//                        image = null,
//                        hasLiked = true,
//                        header = true,
//                        id = i.toLong()
//                    )
//                } else {
//                    User(
//                        name = "local user: $i",
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
//        _localUserList.value = list
//    }

}
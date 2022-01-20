package com.jay.wj_remember.ui.main.fragment.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalFragmentViewModel @Inject constructor() : BaseViewModel() {

    private val _localUserList = MutableLiveData<List<User>>()
    val localUserList: LiveData<List<User>>
        get() = _localUserList

    fun call() {
        val list = mutableListOf<User>().apply {
            for (i in 0..10) {
                val user: User = if (i % 5 == 0) {
                    User(
                        name = "local user: $i",
                        image = null,
                        hasLiked = true,
                        header = true
                    )
                } else {
                    User(
                        name = "local user: $i",
                        image = null,
                        hasLiked = false,
                        header = false
                    )
                }

                add(user)
            }
        }

        _localUserList.value = list
    }

}
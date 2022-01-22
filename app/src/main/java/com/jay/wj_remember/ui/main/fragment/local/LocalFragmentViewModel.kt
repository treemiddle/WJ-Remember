package com.jay.wj_remember.ui.main.fragment.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.LocalUseCase
import com.jay.wj_remember.mapper.Mapper
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
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

    fun localclick(user: User, position: Int) {
        localUseCase.deleteUserLike(Mapper.mapToDomain(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "삭제 성공")
                setNewList(position)
            }, { t ->
                makeLog(javaClass.simpleName, "삭제 실패: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun setNewList(position: Int) {
        val newList = mutableListOf<User>().apply {
            addAll(_localUserList.value!!)
            removeAt(position)
        }

        setLocalUserList(newList)
    }

}
package com.jay.wj_remember.ui.main.fragment.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.CreateUseCase
import com.jay.domain.usecase.LocalUseCase
import com.jay.wj_remember.mapper.Mapper
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * LocalFragment에 있는 event들의 비지니스 로직들을 각 usecase에 요청
 */
@HiltViewModel
class LocalFragmentViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val createUseCase: CreateUseCase
) : BaseViewModel() {

    private val _localUserList = MutableLiveData<List<User>>()
    val localUserList: LiveData<List<User>>
        get() = _localUserList

    fun setLocalUserList(userList: List<User>) {
        _localUserList.value = userList
    }

    fun onClickUser(user: User, position: Int) {
        localUseCase.deleteUserLike(Mapper.mapToDomain(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                submitList(position)
            }, { t ->
                makeLog(javaClass.simpleName, "local delete fail: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun submitList(position: Int) {
        val newList = removeAtIndexFromList(position)

        setLocalUserList(newList)
    }

    private fun removeAtIndexFromList(position: Int): List<User> {
        return createUseCase.removeUserToNewDomainList(
            userList = _localUserList.value!!.map(Mapper::mapToDomain),
            position = position
        ).map(Mapper::mapToPresentation)
    }

}
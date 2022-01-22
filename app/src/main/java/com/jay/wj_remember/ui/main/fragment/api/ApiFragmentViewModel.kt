package com.jay.wj_remember.ui.main.fragment.api

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
 * ApiFragment에 있는 event들의 비지니스 로직들을 각 usecase에 요청
 */
@HiltViewModel
class ApiFragmentViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val createUseCase: CreateUseCase
) : BaseViewModel() {

    private val _apiUserList = MutableLiveData<List<User>>()
    val apiUserList: LiveData<List<User>>
        get() = _apiUserList

    fun setApiUserList(userList: List<User>) {
        _apiUserList.value = userList
    }

    fun onClickUser(user: User, position: Int) {
        if (user.hasLiked) {
            deleteUserLike(user, position)
        } else {
            saveUserLike(user, position)
        }
    }

    private fun saveUserLike(user: User, position: Int) {
        val newUser = createUser(user)

        localUseCase.saveUserLike(Mapper.mapToDomain(newUser))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                submitList(newUser, position)
            }, { t ->
                makeLog(javaClass.simpleName, "api save fail: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun deleteUserLike(user: User, position: Int) {
        val newUser = user.copy(hasLiked = false)

        localUseCase.deleteUserLike(Mapper.mapToDomain(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                submitList(newUser, position)
            }, { t ->
                makeLog(javaClass.simpleName, "api delete fail: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun createUser(user: User): User {
        return Mapper.mapToPresentation(
            createUseCase.createUserForSave(
                Mapper.mapToDomain(user)
            )
        )
    }

    private fun createNewList(user: User, position: Int): List<User> {
        return createUseCase.createNewDomainList(
            userList = _apiUserList.value!!.map(Mapper::mapToDomain),
            user = Mapper.mapToDomain(user),
            position = position
        ).map(Mapper::mapToPresentation)
    }

    private fun submitList(user: User, position: Int) {
        val newList = createNewList(user, position)

        setApiUserList(newList)
    }

}
package com.jay.wj_remember.ui.main.fragment.api

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
class ApiFragmentViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
) : BaseViewModel() {

    private val _apiUserList = MutableLiveData<List<User>>()
    val apiUserList: LiveData<List<User>>
        get() = _apiUserList

    fun setApiUserList(userList: List<User>) {
        _apiUserList.value = userList
    }

    fun apiclick(user: User, position: Int) {
        makeLog(javaClass.simpleName, "api: $user")
        if (user.hasLiked) {
            deleteUserLike(user, position)
        } else {
            saveUserLike(user, position)
        }
    }

    private fun saveUserLike(user: User, position: Int) {
        val newUser = User(
            id = user.id,
            name = user.name,
            image = user.image,
            header = user.header,
            hasLiked = true,
            positionType = user.positionType
        )
        makeLog(javaClass.simpleName, "newUser: $newUser")

        localUseCase.saveUserLike(Mapper.mapToDomain(newUser))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "저장 성공")
                setNewList(newUser, position)
            }, { t ->
                makeLog(javaClass.simpleName, t.localizedMessage)
            }).addTo(compositeDisposable)
    }

    private fun deleteUserLike(user: User, position: Int) {
        val newUser = user.copy(hasLiked = false)
        makeLog(javaClass.simpleName, "newUser: $newUser")

        localUseCase.deleteUserLike(Mapper.mapToDomain(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "삭제 성공")
                setNewList(newUser, position)
            }, { t ->
                makeLog(javaClass.simpleName, t.localizedMessage)
            }).addTo(compositeDisposable)
    }

    private fun setNewList(user: User, position: Int) {
        val newList = mutableListOf<User>().apply {
            addAll(_apiUserList.value ?: emptyList())
            this[position] = user
        }

        setApiUserList(newList)
    }

}
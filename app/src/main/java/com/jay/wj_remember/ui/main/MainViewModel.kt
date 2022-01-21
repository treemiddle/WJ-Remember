package com.jay.wj_remember.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.GithubUseCase
import com.jay.wj_remember.mapper.Mapper
import com.jay.wj_remember.ui.base.BaseViewModel
import com.jay.wj_remember.utils.Event
import com.jay.wj_remember.utils.FragmentType
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
) : BaseViewModel() {

    private val _searchClick = PublishSubject.create<Unit>()
    private val _querySubject = BehaviorSubject.createDefault("")
    private val _tabPositionSubject = BehaviorSubject.createDefault(0)

    private val _fragmentType = MutableLiveData<Event<FragmentType>>()
    val fragmentType: LiveData<Event<FragmentType>>
        get() = _fragmentType

    init {
        val button = _searchClick.throttleFirst(1, TimeUnit.SECONDS)
            .map { _querySubject.value }
        val query = _querySubject.debounce(1500, TimeUnit.MILLISECONDS)

        compositeDisposable.addAll(
            Observable.merge(button, query)
                .filter { it.length >= 2 }
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { showLoading() }
                .switchMapSingle { name ->
                    if (_tabPositionSubject.value == 0) {
                        githubUseCase.searchApiUsers(name)
                            .subscribeOn(Schedulers.io())
                    } else {
                        githubUseCase.searchLocalUsers(name)
                            .subscribeOn(Schedulers.io())
                    }
                }
                .onErrorReturn { listOf() }
                .observeOn(Schedulers.computation())
                .map { it.map(Mapper::mapToPresentation) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { hideLoading() }
                .subscribe { makeLog(javaClass.simpleName, "ok: $it") },

            _tabPositionSubject.distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::fromPositionToFragmentType)
        )
    }

    fun debounceQuery(query: String) = _querySubject.onNext(query)

    fun searchClick() = _searchClick.onNext(Unit)

    fun setTabPosition(position: Int?) {
        position?.let { _tabPositionSubject.onNext(it) }
    }

    private fun fromPositionToFragmentType(fragmentType: Int) {
        if (fragmentType == 0) {
            _fragmentType.value = Event(FragmentType.API)
        } else {
            _fragmentType.value = Event(FragmentType.LOCAL)
        }
    }

}
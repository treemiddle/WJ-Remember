package com.jay.wj_remember

import com.jay.common.makeLog
import com.jay.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
) : BaseViewModel() {

    init {
        githubUseCase.searchUser("_____wj")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "$it")
            }, {
                makeLog(javaClass.simpleName, it.localizedMessage)
            }).addTo(compositeDisposable)
    }

}
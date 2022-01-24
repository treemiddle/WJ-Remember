package com.jay.remote

import com.jay.data.model.DataUser
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.remote.api.GithubApi
import com.jay.remote.mapper.GithubRemoteMapper
import com.jay.remote.model.UserInfo
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote(API)에서 받아온 결과 값을 Data Layer로 매핑하여 내려줌
 */
class GithubRemoteDataSourceImpl @Inject constructor(private val githubApi: GithubApi) : GithubRemoteDataSource {

    /**
     * ==========과제 사용자 이름으로 제한하라==========
     * [in:login] kenya in:login matches users with the word "kenya" in their username.
     * [in:name] bolton in:name matches users whose real name contains the word "bolton.
     * in:name -> github acoount name
     * in:login -> user name
     *
     * in:name으로 처리 시 깃헙 계정과 이름이 다른 경우도 많아 API, LOCAL 화면에 다르게 나타나는 현상 많음
     * (search user api는 login name으로 내려줌)
     *
     * 혹시 몰라 두 가지 모두 구현
     */
    override fun searchUser(name: String): Single<List<DataUser>> {
        //val newName = "$name+in:name"
        val newLogin = "$name+in:login"

        return githubApi.searchUser(newLogin)
            .map { setHeaderFromLogin(it.items) }
            //.map { setHeaderFromName(it.items) }
            .map { it.map(GithubRemoteMapper::mapToData) }
            //.composeDomain()
    }

    private fun setHeaderFromLogin(userList: List<UserInfo>?): List<UserInfo> {
        return if (userList.isNullOrEmpty()) {
            emptyList()
        } else {
            val newList = mutableListOf<UserInfo>()
            val nameGroupList = userList.groupBy { it.login[0] }

            for ((key, value) in nameGroupList) {
                value[0].header = key.toString()
                value.forEach { newList.add(it) }
            }

            return newList
        }
    }

    private fun setHeaderFromName(userList: List<UserInfo>?): List<UserInfo> {
        return if (userList.isNullOrEmpty()) {
            emptyList()
        } else {
            val newList = mutableListOf<UserInfo>()
            val nameGroupList = userList
                .sortedBy { it.login[0] }
                .groupBy { it.login[0] }

            for ((key, value) in nameGroupList) {
                value[0].header = key.toString()
                value.forEach { newList.add(it) }
            }

            return newList
        }
    }

}
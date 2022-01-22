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
     * [in:login] kenya in:login matches users with the word "kenya" in their username.
     * [in:name] bolton in:name matches users whose real name contains the word "bolton.
     * 사용자 이름으로 제한이 실제 이름으로 제한인지 유저의 이름으로 제한인지 몰라 두 가지 경우 모두 구현
     * name기준으로 적용 시 실제 login name과 다른 경우도 있음
     */
    override fun searchUser(name: String): Single<List<DataUser>> {
        val newName = "$name+in:name"
        val newLogin = "$name+in:login"

        return githubApi.searchUser(newLogin)
            .map { setHeaderFromLogin(it.items) }
            //.map { setHeaderFromName(it.items) }
            .map { it.map(GithubRemoteMapper::mapToData) }
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
package com.jay.remote.mapper

import com.jay.data.model.DataUser
import com.jay.data.model.DataUser as DataModel
import com.jay.remote.model.UserInfo as RemoteModel
import com.jay.remote.model.UserInfo

object GithubRemoteMapper : RemoteMapper<RemoteModel, DataModel> {

    override fun mapToData(from: UserInfo): DataUser {
        return DataUser(
            name = from.login,
            image = from.avatar_url,
            id = from.id,
            header = from.header
        )
    }

}
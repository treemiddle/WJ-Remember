package com.jay.remote.mapper

import com.jay.data.model.DataModel
import com.jay.data.model.DataUser
import com.jay.remote.model.UserInfo as RemoteModel

object GithubRemoteMapper : RemoteMapper<RemoteModel, DataModel> {

    override fun mapToData(from: RemoteModel): DataUser {
        return DataUser(
            name = from.login,
            image = from.avatar_url
        )
    }

}
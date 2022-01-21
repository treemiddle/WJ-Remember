package com.jay.local.mapper

import com.jay.data.model.DataUser
import com.jay.local.model.LocalEntity
import com.jay.data.model.DataUser as DataModel
import com.jay.local.model.LocalEntity as LocalModel

object GithubLocalMapper : LocalMapper<DataModel, LocalModel> {

    override fun mapToData(from: LocalModel): DataModel {
        return DataUser(
            name = from.name,
            image = from.image,
            hasLiked = from.hasLiked,
            id = from.id
        )
    }

    override fun mapToLocal(from: DataModel): LocalModel {
        return LocalEntity(
            id = from.id,
            name = from.name,
            image = from.image,
            hasLiked = from.hasLiked
        )
    }

}
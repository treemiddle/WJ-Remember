package com.jay.data.mapper

import com.jay.data.model.DataUser
import com.jay.domain.model.DomainUser
import com.jay.data.model.DataUser as DataModel
import com.jay.domain.model.DomainUser as DomainModel

object DataGithubMapper : DataMapper<DataModel, DomainModel> {

    override fun mapToDomain(from: DataUser): DomainUser {
        return DomainUser(
            name = from.name,
            image = from.image,
            header = from.header,
            id = from.id,
            hasLiked = from.hasLiked
        )
    }

    override fun mapToData(from: DomainUser): DataUser {
        return DataUser(
            name = from.name,
            image = from.image,
            hasLiked = from.hasLiked,
            header = from.header,
            id = from.id
        )
    }

}
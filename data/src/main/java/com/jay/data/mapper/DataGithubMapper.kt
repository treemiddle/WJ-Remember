package com.jay.data.mapper

import com.jay.data.model.DataUser
import com.jay.data.model.DataUser as DataModel
import com.jay.domain.model.DomainUser as DomainModel

object DataGithubMapper : DataMapper<DataModel, DomainModel> {

    override fun mapToDomain(from: DataModel): DomainModel {
        return DomainModel(
            name = from.name,
            image = from.image
        )
    }

    override fun mapToData(from: DomainModel): DataModel {
        return DataUser(
            name = from.name,
            image = from.image,
            hasLiked = from.hasLiked
        )
    }

}
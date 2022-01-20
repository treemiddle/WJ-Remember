package com.jay.wj_remember.mapper

import com.jay.domain.model.DomainUser
import com.jay.wj_remember.model.User
import com.jay.domain.model.DomainUser as DomainModel
import com.jay.wj_remember.model.User as PresentationModel


object Mapper : PresentationMapper<DomainModel, PresentationModel> {

    override fun mapToPresentation(from: DomainUser): User {
        return User(
            name = from.name,
            image = from.image,
            hasLiked = from.hasLiked,
            header = from.header
        )
    }

    override fun mapToDomain(from: User): DomainUser {
        return DomainUser(
            name = from.name,
            image = from.image,
            hasLiked = from.hasLiked
        )
    }

}
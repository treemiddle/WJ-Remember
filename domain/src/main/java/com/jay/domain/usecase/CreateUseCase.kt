package com.jay.domain.usecase

import com.jay.domain.model.DomainUser

/**
 * Presentation에 있는 요구사항을 만들어서 반환
 */
class CreateUseCase {

    fun createUserForSave(user: DomainUser): DomainUser {
        return DomainUser(
            id = user.id,
            name = user.name,
            image = user.image,
            header = user.header,
            hasLiked = true
        )
    }

    fun createNewDomainList(
        userList: List<DomainUser>,
        user: DomainUser,
        position: Int
    ): List<DomainUser> {
        val newList = mutableListOf<DomainUser>().apply {
            addAll(userList)
            this[position] = user
        }

        return newList
    }

    fun removeAtDomainList(
        userList: List<DomainUser>,
        position: Int
    ): List<DomainUser> {
        val newList = mutableListOf<DomainUser>().apply {
            addAll(userList)
            removeAt(position)
        }

        return newList
    }

}
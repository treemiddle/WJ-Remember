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

    fun removeUserToNewDomainList(userList: List<DomainUser>, position: Int): List<DomainUser> {
        return removeUser(userList, position)
    }

    private fun removeUser(userList: List<DomainUser>, position: Int): List<DomainUser> {
        val newList = mutableListOf<DomainUser>().apply {
            addAll(userList)

            if (size != 0) {
                removeAt(position)
            }
        }

        return changeHeaderToNextNode(
            header = userList[position].header,
            userList = newList,
            position = position
        )
    }

    private fun changeHeaderToNextNode(
        header: String?,
        userList: List<DomainUser>,
        position: Int
    ): List<DomainUser> {
        val newList = mutableListOf<DomainUser>().apply { addAll(userList) }

        return if (header.isNullOrEmpty()) {
            newList
        } else {
            val groupList = newList.filter { it.name.startsWith(header) }

            if (groupList.isNotEmpty()) {
                val node = groupList[0].apply { this.header = header }
                newList[position] = node
            }

            newList
        }
    }

}
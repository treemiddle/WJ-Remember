package com.jay.wj_remember.mapper

import com.jay.domain.model.DomainModel
import com.jay.wj_remember.model.PresentationModel

interface PresentationMapper<D : DomainModel, P : PresentationModel> {

    fun mapToPresentation(from: D): P

    fun mapToDomain(from: P): D

}
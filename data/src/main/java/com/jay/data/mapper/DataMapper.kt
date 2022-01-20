package com.jay.data.mapper

import com.jay.data.model.DataModel
import com.jay.domain.model.DomainModel

interface DataMapper<Data : DataModel, Domain : DomainModel> {

    fun mapToDomain(from: Data): Domain

    fun mapToData(from: Domain): Data

}




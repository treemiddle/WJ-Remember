package com.jay.local.mapper

import com.jay.data.model.DataModel
import com.jay.local.model.LocalModel

interface LocalMapper<D : DataModel, L : LocalModel> {

    fun mapToData(from: L): D

    fun mapToLocal(from: D): L

}
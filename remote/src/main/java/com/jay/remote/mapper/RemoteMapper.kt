package com.jay.remote.mapper

import com.jay.data.model.DataModel
import com.jay.remote.model.RemoteModel

interface RemoteMapper<R : RemoteModel, D : DataModel> {

    fun mapToData(from: R): D

}
package com.jml.github.challenge.data.datasource.cache

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class MemoryCache<T> {

    private val dataFlow = MutableSharedFlow<List<T>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val dataList = mutableListOf<T>()

    fun addItems(item: List<T>){
        dataList.addAll(item)
        dataFlow.tryEmit(dataList.toList())
    }

    fun getCurrentData(): List<T> {
        return dataList.toList()
    }

    fun observeChanges(): Flow<List<T>> {
        return dataFlow
    }
}
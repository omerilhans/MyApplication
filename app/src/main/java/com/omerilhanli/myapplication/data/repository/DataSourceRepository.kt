package com.omerilhanli.myapplication.data.repository

import com.omerilhanli.myapplication.data.datasource.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceRepository @Inject
constructor(private val dataSource: DataSource) {

    fun fetchPeople(nextPage: String?, fetchCompletionHandler: FetchCompletionHandler) {
        dataSource.fetch(next = nextPage, fetchCompletionHandler)
    }
}


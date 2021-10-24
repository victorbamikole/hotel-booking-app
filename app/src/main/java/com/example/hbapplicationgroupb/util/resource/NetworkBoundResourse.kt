package com.example.hbapplicationgroupb.util.resource

import kotlinx.coroutines.flow.*

fun <ResultType,RequestType> networkBoundResource(
    dbQueryFunc:()-> Flow<ResultType>,
    fetchDataToBeSavedToDbFunc:suspend ()-> RequestType,
    saveFetchedResultToDb:suspend (RequestType)->Unit,
    shouldFetch:(ResultType)->Boolean = {true}
) = flow {
    val data = dbQueryFunc().first()
    val result = if (shouldFetch(data)){
        emit(Resource.Loading(data))
        try {
            saveFetchedResultToDb(fetchDataToBeSavedToDbFunc())
            dbQueryFunc().map { Resource.Success(it) }
        }catch (t:Throwable){
            dbQueryFunc().map { Resource.Error(t,it) }
        }
    }else{
        dbQueryFunc().map { Resource.Success(it) }
    }
    emitAll(result)
}
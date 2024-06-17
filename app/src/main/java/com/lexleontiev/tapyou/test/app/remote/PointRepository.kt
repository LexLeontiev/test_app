package com.lexleontiev.tapyou.test.app.remote

import com.lexleontiev.tapyou.test.app.data.Point
import com.lexleontiev.tapyou.test.app.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


interface PointRepository {
    suspend fun getPoints(count: Int): NetworkResult<PointsResponse>
}

class PointRepositoryImpl(private val api: Api = Injector.inject(Api::class))
    : PointRepository, ApiHandler {

    override suspend fun getPoints(count: Int): NetworkResult<PointsResponse> = withContext(Dispatchers.Default) {
        handleApi { api.getPoints(count) }
    }
}

class PointRepositoryMock : PointRepository {

    override suspend fun getPoints(count: Int): NetworkResult<PointsResponse> = withContext(Dispatchers.Default) {
        delay(500)
        NetworkResult.Success(200, PointsResponse(listOf(Point(-44.57f, 18.84f), Point(12.1f, 53.02f), Point(17.09f, 51.59f))))
    }
}

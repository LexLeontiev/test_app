package com.lexleontiev.tapyou.test.app.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("/api/test/points")
    suspend fun getPoints(
        @Query("count") count: Int
    ): Response<PointsResponse>
}

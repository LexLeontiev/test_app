package com.lexleontiev.tapyou.test.app.remote

import com.lexleontiev.tapyou.test.app.data.Point

//{"points":[{"x":17.09,"y":51.59},{"x":-44.57,"y":18.84},{"x":12.10,"y":53.02}]}
data class PointsResponse(
    val points: List<Point>
)
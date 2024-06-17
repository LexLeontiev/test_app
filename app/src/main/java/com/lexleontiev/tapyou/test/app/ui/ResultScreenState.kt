package com.lexleontiev.tapyou.test.app.ui

import com.lexleontiev.tapyou.test.app.data.Point


sealed class ResultScreenState {
    data object Loading : ResultScreenState()
    class Data(val data: List<Point>): ResultScreenState()
    // we can pass here errorCode/object and handle it for different errors, but it isn't an
    // objective of the test. Just FYI
    data object Error: ResultScreenState()
}

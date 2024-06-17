package com.lexleontiev.tapyou.test.app.ui.compose


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexleontiev.tapyou.test.app.data.Point
import com.lexleontiev.tapyou.test.app.ui.ResultScreenState
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.extension.floor
import com.patrykandpatrick.vico.core.marker.Marker
import kotlin.math.min


@Composable
fun ChartView(
    state: State<ResultScreenState>
) {
    when (val chartState = state.value) {
        is ResultScreenState.Loading, is ResultScreenState.Error -> { /* no-op */ }
        is ResultScreenState.Data -> {
            // idk but the chart behaves in a strange way if it works with negative numbers, so I
            // just shift all numbers to be positive. It doesn't make the chart incorrect, because
            // all relative values are preserved
            val minX = min(chartState.data.minOf { it.x }, 0f)
            val fixedData = chartState.data.map { Pair(it, Point(((it.x - minX)*100).floor, (it.y*100).floor )) }
            val map = LinkedHashMap<Float, Float>()
            fixedData.forEach {
                map[it.second.x] = it.second.y
            }
            val markersMap = LinkedHashMap<Float, Marker>()
            fixedData.forEach {
                markersMap[it.second.x] = rememberMarker()
            }

            val chartEntryModel = map.keys.zip(map.values) { x, y ->
                entryOf(x, y)
            }.let {
                entryModelOf(it)
            }
            Chart(
                modifier = Modifier.padding(all = 12.dp),
                chart = lineChart(
                    persistentMarkers = markersMap
                ),
                model = chartEntryModel,
                topAxis = null,
                bottomAxis = null,
                endAxis = null,
                chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = true),
                isZoomEnabled = true,
                horizontalLayout = HorizontalLayout.FullWidth(),
                getXStep = { (it.maxX - it.minX) / 3 }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChartViewPreview() {
    val sampleData = mutableListOf<Point>()
    sampleData.add(Point(-44.57f,18.84f))
    sampleData.add(Point(12.1f,53.02f))
    sampleData.add(Point(17.09f,51.59f))
    val mutableState = remember {
        mutableStateOf<ResultScreenState>(ResultScreenState.Data(sampleData))
    }
    ChartView(
        state = mutableState
    )
}
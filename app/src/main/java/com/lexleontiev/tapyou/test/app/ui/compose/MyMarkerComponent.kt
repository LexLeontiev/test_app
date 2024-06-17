package com.lexleontiev.tapyou.test.app.ui.compose

import android.graphics.RectF
import com.patrykandpatrick.vico.core.component.Component
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.cornered.MarkerCorneredShape
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.component.text.VerticalPosition
import com.patrykandpatrick.vico.core.context.DrawContext
import com.patrykandpatrick.vico.core.extension.half
import com.patrykandpatrick.vico.core.extension.orZero
import com.patrykandpatrick.vico.core.marker.Marker


internal open class MyMarkerComponent(
    label: TextComponent,
    indicator: Component?,
    guideline: LineComponent?
) : MarkerComponent(label, indicator, guideline) {

    private val tempBounds2 = RectF()

    private val TextComponent.tickSizeDp2: Float
        get() = ((background as? ShapeComponent)?.shape as? MarkerCorneredShape)?.tickSizeDp.orZero

    init {
        labelFormatter = MyLabelFormatter
    }

    override fun draw(
        context: DrawContext,
        bounds: RectF,
        markedEntries: List<Marker.EntryModel>,
    ): Unit = with(context) {
        val halfIndicatorSize = indicatorSizeDp.half.pixels

        markedEntries.forEachIndexed { _, model ->
            onApplyEntryColor?.invoke(model.color)
            indicator?.draw(
                context,
                model.location.x - halfIndicatorSize,
                model.location.y - halfIndicatorSize,
                model.location.x + halfIndicatorSize,
                model.location.y + halfIndicatorSize,
            )
        }
        drawLabel2(context, bounds, markedEntries)
    }

    private fun drawLabel2(
        context: DrawContext,
        bounds: RectF,
        markedEntries: List<Marker.EntryModel>
    ): Unit = with(context) {
//        val text = labelFormatter.getLabel(markedEntries)
        val text = ""
        val entryX = markedEntries.averageOf { it.location.x }
        val labelBounds = label.getTextBounds(context, text, outRect = tempBounds2)
        val halfOfTextWidth = labelBounds.width().half
        val halfIndicatorSize = indicatorSizeDp.half.pixels
        val x = overrideXPositionToFit(entryX, bounds, halfOfTextWidth)
        val y =
            markedEntries.first().location.y - halfIndicatorSize - labelBounds.height() - label.tickSizeDp2.pixels
        this[MarkerCorneredShape.tickXKey] = entryX

        label.drawText(
            context = context,
            text = text,
            textX = x,
            textY = y,
            verticalPosition = VerticalPosition.Bottom,
        )
    }

    private fun overrideXPositionToFit(
        xPosition: Float,
        bounds: RectF,
        halfOfTextWidth: Float,
    ): Float = when {
        xPosition - halfOfTextWidth < bounds.left -> bounds.left + halfOfTextWidth
        xPosition + halfOfTextWidth > bounds.right -> bounds.right - halfOfTextWidth
        else -> xPosition
    }

    private fun <T> Collection<T>.averageOf(selector: (T) -> Float): Float =
        fold(0f) { sum, element ->
            sum + selector(element)
        } / size

}
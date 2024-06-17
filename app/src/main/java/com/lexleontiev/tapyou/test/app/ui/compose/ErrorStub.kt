package com.lexleontiev.tapyou.test.app.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lexleontiev.tapyou.test.app.R


@Composable
fun ErrorStub() = Text(
    text = stringResource(id = R.string.error),
    color = MaterialTheme.colorScheme.onBackground
)


@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun ErrorStubPreview() = MaterialTheme {
    ErrorStub()
}

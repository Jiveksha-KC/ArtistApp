package com.artistapp.ui.styles

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.ui.theme.ArtistAppTheme

/**
 * IMPLEMENTATION
 */

@Composable
fun StyledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(text = text)
    }
}

/**
 * PREVIEW
 */
@Preview(name = "StyledButtonEnabled Light")
@Preview(name = "StyledButtonEnabled Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun StyledButtonEnabledPreview() {
    ArtistAppTheme {
        StyledButton(onClick = {}, text = "Some Button")
    }
}

@Preview(name = "StyledButtonDisabled Light")
@Preview(name = "StyledButtonDisabled Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun StyledButtonDisabledPreview() {
    ArtistAppTheme {
        StyledButton(onClick = {}, text = "Some Button", enabled = false)
    }
}
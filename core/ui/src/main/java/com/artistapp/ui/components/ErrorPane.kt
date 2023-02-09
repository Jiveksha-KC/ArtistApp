package com.artistapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.ui.styles.StyledButton
import com.artistapp.ui.theme.ArtistAppTheme
import com.artistapp.resource.R

@Composable
fun ErrorPane(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    retryAction: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = body,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            StyledButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { retryAction() },
                text = stringResource(id = R.string.general_retry_button)
            )
        }
    }
}

@Composable
fun ErrorPane(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @StringRes bodyRes: Int,
    retryAction: () -> Unit
) {
    ErrorPane(
        modifier = modifier,
        title = stringResource(id = titleRes),
        body = stringResource(id = bodyRes),
        retryAction = retryAction
    )
}

@Composable
fun GenericErrorPane(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    ErrorPane(
        modifier = modifier,
        titleRes = R.string.general_generic_error_title,
        bodyRes = R.string.general_generic_error_message,
        retryAction = retryAction
    )
}

@Composable
fun NetworkErrorPane(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    ErrorPane(
        modifier = modifier,
        titleRes = R.string.general_network_error_title,
        bodyRes = R.string.general_network_error_message,
        retryAction = retryAction
    )
}

/**
 * PREVIEW
 */

@Preview(name = "ErrorPane Light")
@Preview(name = "ErrorPane Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ErrorPanePreview() {
    ArtistAppTheme {
        ErrorPane(
            title = "Oops I did it again",
            body = "I played with your heart. Got lost in the game, oh baby baby",
            retryAction = {}
        )
    }
}
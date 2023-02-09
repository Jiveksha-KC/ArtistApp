package com.artistapp.artist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.artistapp.model.artist.Artist
import com.artistapp.resource.R

@Composable
internal fun ArtistDetailsHeader(artist: Artist) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = artist.name,
            style = MaterialTheme.typography.h6
        )
        Divider(color = MaterialTheme.colors.primary)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .semantics(mergeDescendants = true) {},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 12.dp),
                color = MaterialTheme.colors.onSecondary,
                text = stringResource(id = R.string.artist_gender_label),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = artist.gender,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
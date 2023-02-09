package com.artistapp.artist.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.model.album.Album
import com.artistapp.ui.components.shimmerPlaceholder
import com.artistapp.ui.theme.ArtistAppTheme

@Composable
internal fun AlbumItem(
    isLoading: Boolean,
    album: Album
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier,
                text = album.firstReleaseDate,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                modifier = Modifier.shimmerPlaceholder(isLoading),
                text = album.title,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(name = "AlbumItem Light")
@Preview(name = "AlbumItem Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewArtistListItem() {
    ArtistAppTheme {
        AlbumItem(
            isLoading = false,
            album = Album(
                id = "29762c82-bb92-4acd-b1fb-09cc4da250d2",
                title = "Ricky Martin",
                firstReleaseDate = "1993-10-26"
            )
        )
    }
}
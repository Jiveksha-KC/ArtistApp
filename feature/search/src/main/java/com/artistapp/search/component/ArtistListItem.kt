package com.artistapp.search.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.ArtistLifeSpan
import com.artistapp.ui.theme.ArtistAppTheme

@Composable
internal fun ArtistListItem(
    title: String,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .clickable { onItemClick(title) }
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = title.uppercase(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

private val placeHolderArtist =
    Artist(
        id = "1",
        name = "FirstName",
        gender = "Male",
        lifeSpan = ArtistLifeSpan(
            "1956-07-15", "", false
        ),
        country = "USA",
        sortName = "firstName, LastName"
    )

@Preview(name = "ArtistListItem Light")
@Preview(name = "ArtistListItem Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewArtistListItem() {
    ArtistAppTheme {
        ArtistListItem(
            title = placeHolderArtist.name,
            onItemClick = {}
        )
    }
}

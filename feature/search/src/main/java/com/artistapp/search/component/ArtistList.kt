package com.artistapp.search.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.ArtistLifeSpan
import com.artistapp.model.search.SearchArtistResult
import com.artistapp.ui.theme.ArtistAppTheme
import com.artistapp.ui.theme.genericHorizontalContentPadding

@Composable
internal fun ArtistList(
    modifier: Modifier,
    searchResult: SearchArtistResult,
    onArtistSelected: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(
                PaddingValues(
                    vertical = 8.dp,
                    horizontal = genericHorizontalContentPadding
                )
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }
        items(searchResult.artists) { artist ->
            ArtistListItem(
                title = artist.name,
                onItemClick = {
                    onArtistSelected(artist.id)
                })
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

private val placeholderSearchResult =
    SearchArtistResult(
        artists = listOf(
            Artist(
                country = "US",
                gender = "Male",
                id = "29762c82-bb92-4acd-b1fb-09cc4da250d2",
                lifeSpan = ArtistLifeSpan(
                    begin = "1993-10-26",
                    end = "1993-10-26",
                    ended = false
                ),
                name = "Ricky Martin",
                sortName = "Martin, Ricky",
            )
        ),
        count = 25,
        created = "1993-10-26",
        offset = 0
    )


@Preview(name = "ArtistList Light")
@Preview(name = "ArtistList Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewArtistList() {
    ArtistAppTheme {
        ArtistList(
            modifier = Modifier,
            searchResult = placeholderSearchResult,
            onArtistSelected = {}
        )
    }
}

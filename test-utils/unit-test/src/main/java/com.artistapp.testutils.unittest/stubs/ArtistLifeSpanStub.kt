import com.artistapp.model.artist.ArtistLifeSpan

@Suppress("MemberVisibilityCanBePrivate")
object ArtistLifeSpanStub {

    const val BEGIN = "1993-10-26"
    const val END = "1993-10-26"
    const val ENDED = false

    fun new(
        begin: String = BEGIN,
        end: String = END,
        ended: Boolean = ENDED
    ) =
        ArtistLifeSpan(
            begin = begin,
            end = end,
            ended = ended
        )
}
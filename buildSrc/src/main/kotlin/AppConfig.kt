@Suppress("unused", "MemberVisibilityCanBePrivate")
object AppConfig {

    const val APP_ID = "com.artistapp"
    const val BASE_VERSION_NAME = "0.0.1"
    const val DEFAULT_VERSION_CODE = 1

    val BASE_PACKAGE = APP_ID.replace(".", "/")
}
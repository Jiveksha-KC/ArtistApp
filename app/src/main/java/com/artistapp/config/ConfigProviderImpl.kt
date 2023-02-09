package com.artistapp.config

import com.artistapp.BuildConfig
import com.artistapp.common.config.ConfigProvider

object ConfigProviderImpl : ConfigProvider {

    override val isDebug = BuildConfig.DEBUG

    override val apiBaseUrl = "https://musicbrainz.org/ws/2/"
}
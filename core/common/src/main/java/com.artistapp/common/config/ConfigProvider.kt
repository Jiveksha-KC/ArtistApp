package com.artistapp.common.config

interface ConfigProvider {

    val isDebug: Boolean

    val apiBaseUrl: String
}
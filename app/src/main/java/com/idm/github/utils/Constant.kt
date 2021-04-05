package com.idm.github.utils

import com.idm.github.BuildConfig

class Constant {
    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val API_KEY = BuildConfig.TOKEN
    }
}
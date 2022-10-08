package com.kaliondroid.techarena.utils

import com.kaliondroid.techarena.BuildConfig

const val API_KEY = BuildConfig.API_KEY
const val CATEGORY = "technology"
const val BASE_URL = "http://api.mediastack.com/v1/news?access_key=$API_KEY&categories=$CATEGORY"

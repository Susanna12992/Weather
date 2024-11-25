package com.susanna.weatherapp.utils

import com.susanna.weatherapp.R
import com.susanna.weatherapp.network.Result

fun DataError.asUiText(): UiText {
    return when (this) {
        is DataError.Network -> when (this) {
            DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.msg_the_request_timed_out)
            DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.msg_youve_hit_your_rate_limit)
            DataError.Network.NO_INTERNET -> UiText.StringResource(R.string.msg_no_internet)
            DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.msg_file_too_large)
            DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.msg_server_error)
            DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.msg_error_serialization)
            DataError.Network.UNKNOWN -> UiText.StringResource(R.string.msg_unknown_error)
        }

        is DataError.CustomError -> UiText.DynamicString(this.message)
    }
}
fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}
package com.jml.github.challenge.data.extension

import retrofit2.Response

fun <dataPayload, resultContent> Response<dataPayload>.onResponse(
    success: (dataPayload) -> Result<resultContent>,
    fail: (Response<dataPayload>) -> Result<resultContent>
): Result<resultContent> {

    val body = body()
    return if (this.isSuccessful && body != null ) {
        success(body as dataPayload)
    } else {
        fail(this)
    }
}

package com.tt.handsomeman.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class StandardResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("statusCode")
    @Expose
    var statusCode: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
}

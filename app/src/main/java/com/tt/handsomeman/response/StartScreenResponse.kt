package com.tt.handsomeman.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StartScreenResponse : StandardResponse() {
    @SerializedName("data")
    @Expose
    var data: StartScreenData? = null
}

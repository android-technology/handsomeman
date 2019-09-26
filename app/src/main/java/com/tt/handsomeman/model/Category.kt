package com.tt.handsomeman.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category(@field:SerializedName("name")
               @field:Expose
               var name: String?) {

    @SerializedName("id")
    @Expose
    var id: Int? = null

}
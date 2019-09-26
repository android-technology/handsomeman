package com.tt.handsomeman.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Job(@field:SerializedName("createTime")
          @field:Expose
          var createTime: Date?, @field:SerializedName("title")
          @field:Expose
          var title: String?, @field:SerializedName("budgetMin")
          @field:Expose
          var budgetMin: Int?, @field:SerializedName("budgetMax")
          @field:Expose
          var budgetMax: Int?, @field:SerializedName("deadline")
          @field:Expose
          var deadline: Date?, @field:SerializedName("location")
          @field:Expose
          var location: String?) {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("categoryId")
    @Expose
    var categoryId: Int? = null
    @SerializedName("detail")
    @Expose
    var detail: String? = null
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @SerializedName("lng")
    @Expose
    var lng: Double? = null
    @SerializedName("bidMin")
    @Expose
    var bidMin: Int? = null
    @SerializedName("bidMax")
    @Expose
    var bidMax: Int? = null
    @SerializedName("interviewing")
    @Expose
    var interviewing: Int? = null
    @SerializedName("handymanId")
    @Expose
    var handymanId: Int? = null
    @SerializedName("accountId")
    @Expose
    var accountId: Int? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

    fun setCreateTimeBinding(createTime: Date): String {
        val myFormat = "h" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(createTime) + " hour(s) ago"
    }

    fun setBudgetRange(budgetMin: Int?, budgetMax: Int?): String {
        return "$ $budgetMin - $$budgetMax"
    }

    fun setLocationBinding(location: String): String {
        var location = location
        if (location.length > 10) {
            location = location.substring(0, 11) + "..."
        }
        return location
    }

    fun setDeadlineBinding(deadline: Date): String {
        val myFormat = "dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(deadline) + " day(s) left"
    }
}

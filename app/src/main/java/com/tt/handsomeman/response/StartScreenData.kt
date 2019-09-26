package com.tt.handsomeman.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tt.handsomeman.model.Category
import com.tt.handsomeman.model.Job

class StartScreenData(@field:SerializedName("jobList")
                      @field:Expose
                      var jobList: List<Job>?, @field:SerializedName("categoryList")
                      @field:Expose
                      var categoryList: List<Category>?)

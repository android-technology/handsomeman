package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartScreenResponse extends StandardResponse {
    @SerializedName("data")
    @Expose
    private StartScreenData data;

    public StartScreenData getData() {
        return data;
    }

    public void setData(StartScreenData data) {
        this.data = data;
    }
}

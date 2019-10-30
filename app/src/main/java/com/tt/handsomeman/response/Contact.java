package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("data")
    @Expose
    private String avatar;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isHeader")
    @Expose
    private boolean isHeader;

    public String getFirstCharacter(String name){
        return String.valueOf(name.toUpperCase().charAt(0));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsHeader() {
        return isHeader;
    }

    public void setIsHeader(boolean isheader) {
        isHeader = isheader;
    }
}

package com.tt.handsomeman.response;

public class Contact {
    private int id;
    private String avatar;
    private String name;
    private boolean isHeader;
    private long updateDate;

    public String getFirstCharacter(String name) {
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

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}

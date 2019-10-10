package com.tt.handsomeman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerReview {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("handymanId")
    @Expose
    private Integer handymanId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("vote")
    @Expose
    private Integer vote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(Integer handymanId) {
        this.handymanId = handymanId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}

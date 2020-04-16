package com.tt.handsomeman.request;

public class ReviewRequest {
    private int jobId;
    private int receiverId;
    private int vote;
    private String comment;

    public ReviewRequest(int jobId,
                         int receiverId,
                         int vote,
                         String comment) {
        this.jobId = jobId;
        this.receiverId = receiverId;
        this.vote = vote;
        this.comment = comment;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

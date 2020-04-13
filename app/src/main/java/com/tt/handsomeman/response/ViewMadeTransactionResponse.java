package com.tt.handsomeman.response;

import java.util.List;

public class ViewMadeTransactionResponse {
    private List<JobTransactionResponse> jobTransactionResponseList;
    private List<PayoutResponse> payoutResponseList;

    public List<JobTransactionResponse> getJobTransactionResponseList() {
        return jobTransactionResponseList;
    }

    public void setJobTransactionResponseList(List<JobTransactionResponse> jobTransactionResponseList) {
        this.jobTransactionResponseList = jobTransactionResponseList;
    }

    public List<PayoutResponse> getPayoutResponseList() {
        return payoutResponseList;
    }

    public void setPayoutResponseList(List<PayoutResponse> payoutResponseList) {
        this.payoutResponseList = payoutResponseList;
    }
}

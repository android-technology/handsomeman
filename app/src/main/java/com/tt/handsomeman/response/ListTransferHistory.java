package com.tt.handsomeman.response;

import java.util.List;

public class ListTransferHistory {
    private List<TransferHistoryResponse> transferHistoryResponseList;

    public List<TransferHistoryResponse> getTransferHistoryResponseList() {
        return transferHistoryResponseList;
    }

    public void setTransferHistoryResponseList(List<TransferHistoryResponse> transferHistoryResponseList) {
        this.transferHistoryResponseList = transferHistoryResponseList;
    }
}
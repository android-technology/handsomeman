package com.tt.handsomeman.response;

import java.util.List;

public class ListCustomerTransfer {
    private List<CustomerTransferHistoryResponse> customerTransferHistoryList;

    public List<CustomerTransferHistoryResponse> getCustomerTransferHistoryList() {
        return customerTransferHistoryList;
    }

    public void setCustomerTransferHistoryList(List<CustomerTransferHistoryResponse> customerTransferHistoryList) {
        this.customerTransferHistoryList = customerTransferHistoryList;
    }
}

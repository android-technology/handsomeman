package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Category;

import java.util.List;

public class StartScreenCustomer {

    private List<HandymanResponse> handymanResponsesList;
    private List<Category> categoryList;

    public List<HandymanResponse> getHandymanResponsesList() {
        return handymanResponsesList;
    }

    public void setHandymanResponsesList(List<HandymanResponse> handymanResponsesList) {
        this.handymanResponsesList = handymanResponsesList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}

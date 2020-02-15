package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Category;

import java.util.List;

public class ListCategory {
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}

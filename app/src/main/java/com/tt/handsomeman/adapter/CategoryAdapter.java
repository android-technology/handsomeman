package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Category;

import java.util.List;

public class CategoryAdapter extends BaseRecycleViewAdapter {

    private List<Category> categoryList;
    private Context context;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    protected Object getObjForPosition(int position) {
        return categoryList.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_category;
    }

    @Override
    protected View.OnClickListener getOnItemClickListener(int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, categoryList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}

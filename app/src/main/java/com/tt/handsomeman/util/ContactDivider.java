package com.tt.handsomeman.util;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.adapter.ContactAdapter;

public class ContactDivider extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public ContactDivider(Drawable divider) {
        mDivider = divider;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int dividerLeftWithPadding = DensityPixelToPixel.dpToPx(15, parent.getContext());
        int dividerRightWithPadding = parent.getWidth() - DensityPixelToPixel.dpToPx(15, parent.getContext());

        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i <= childCount - 2; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

            View viewNext = parent.getChildAt(i + 1);
            int positionNext = parent.getChildAdapterPosition(viewNext);
            int viewTypeNext = parent.getAdapter().getItemViewType(positionNext);

            if (viewTypeNext == ContactAdapter.HEADER) {
                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            } else {
                mDivider.setBounds(dividerLeftWithPadding, dividerTop, dividerRightWithPadding, dividerBottom);
                mDivider.draw(canvas);
            }
        }
    }
}

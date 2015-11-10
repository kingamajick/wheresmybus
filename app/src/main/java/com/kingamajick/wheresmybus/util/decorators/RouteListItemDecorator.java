package com.kingamajick.wheresmybus.util.decorators;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kingamajick.wheresmybus.R;

public class RouteListItemDecorator extends RecyclerView.ItemDecoration {

    private final int mFirstLeftMargin;
    private final int mLastRightMargin;
    private final int mMargin;

    public RouteListItemDecorator(@NonNull Context context) {
        final TypedArray ta = context.getTheme().obtainStyledAttributes(
                new int[]{
                        android.R.attr.listPreferredItemPaddingLeft,
                        android.R.attr.listPreferredItemPaddingRight});

        mFirstLeftMargin = ta.getDimensionPixelSize(0, 0);
        mLastRightMargin = ta.getDimensionPixelSize(0, 0);
        ta.recycle();
        mMargin = context.getResources().getDimensionPixelSize(R.dimen.route_item_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildAdapterPosition(view);
        final int totalCount = parent.getAdapter().getItemCount();

        if (position == 0) {
            // First item
            outRect.set(mFirstLeftMargin, mMargin, mMargin, mMargin);
        } else if (position == totalCount - 1) {
            // Last item
            outRect.set(mMargin, mMargin, mLastRightMargin, mMargin);
        } else {
            // Others
            outRect.set(mMargin, mMargin, mMargin, mMargin);
        }
    }
}

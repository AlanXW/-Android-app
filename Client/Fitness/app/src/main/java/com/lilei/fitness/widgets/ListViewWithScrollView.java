package com.lilei.fitness.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ListViewWithScrollView extends ListView {
    public ListViewWithScrollView(Context context) {
        super(context);
    }

    public ListViewWithScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewWithScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

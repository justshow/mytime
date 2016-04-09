package com.timenet.timenet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 我是奋斗 on 2016/4/9.
 */
public class NoScrollGridView extends GridView {
    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //第一个参数决定布局空间的大小，第二个参数是布局模式，at_most指子view需要多大空间就扩展到多大空间
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

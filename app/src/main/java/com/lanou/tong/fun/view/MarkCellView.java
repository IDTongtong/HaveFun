package com.lanou.tong.fun.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lanou.tong.fun.R;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.views.BaseMarkView;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.vo.DayData;


/**
 * Created by bob.sun on 15/8/30.
 */
public class MarkCellView extends BaseMarkView {
    public MarkCellView(Context context) {
        super(context);
    }

    public MarkCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setDisplayText(DayData day) {
        ((TextView) this.findViewById(R.id.id_cell_text)).setText(day.getText());
    }
}

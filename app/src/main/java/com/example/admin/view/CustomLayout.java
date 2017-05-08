package com.example.admin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class CustomLayout extends ViewGroup{

    private static final int LEFTOFFSET =100;

    private Paint p;

    public CustomLayout(Context context) {
        this(context,null);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setAntiAlias(true);
        setWillNotDraw(false);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childcount=getChildCount();
        int preTotalHeight=getPaddingTop();

        for(int i=0;i<childcount;i++){
            View child=getChildAt(i);
            child.measure(MeasureSpec.AT_MOST+(getWidth()-i* LEFTOFFSET-getPaddingRight()-getPaddingLeft()),MeasureSpec.UNSPECIFIED);
            child.layout(i* LEFTOFFSET+(i==0?getPaddingLeft():0),preTotalHeight,i* LEFTOFFSET +child.getMeasuredWidth(),preTotalHeight+child.getMeasuredHeight());
            preTotalHeight=preTotalHeight+child.getMeasuredHeight();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h=getHeight();
        int w=getWidth();
        int radius=h>w?w/2:h/2;
        int x=h>w?w/2:h/2;
        int y=h>w?w/2:h/2;
        p.setColor(Color.parseColor(getRandColorCode()));
        canvas.drawCircle(x,y,radius,p);
        int childcount=getChildCount();
        for(int i=0;i<childcount;i++){
            p.setColor(Color.parseColor(getRandColorCode()));
            View child=getChildAt(i);
            canvas.drawRect(child.getLeft(),child.getTop(),child.getRight(),child.getBottom(),p);
        }
    }

    private String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();
        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;
        return "#"+r+g+b;
    }
}

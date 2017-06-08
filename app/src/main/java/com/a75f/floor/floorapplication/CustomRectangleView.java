package com.a75f.floor.floorapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by AGoyal on 6/8/2017.
 */

public class CustomRectangleView extends View {
    private Paint paint;
    private Paint textpaint;
    private Paint crossPaint;
    private ArrayList<Item> mItems;
    private Context mContext;
    public CustomRectangleView(Context context, ArrayList<Item> items) {
        super(context);
        mContext = context;
        mItems = items;
        paint = new Paint();
        paint.setTextSize(25);
        textpaint = new Paint();
        textpaint.setColor(Color.BLACK);
        textpaint.setStyle(Paint.Style.STROKE);
        crossPaint = new Paint();
        crossPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(mContext.getResources().getColor(R.color.floorcolor));
        int padding = mContext.getResources().getDimensionPixelOffset(R.dimen.floor_padding);
        canvas.drawRect(padding, padding, getMax(0) + getMax(2)+padding, getMax(1)+getMax(3)+5*padding, textpaint);
        for(int i =0; i<mItems.size(); i++) {
            float xPos = (float)mItems.get(i).getxPos();
            float yPos = (float)mItems.get(i).getyPos();
            float width = (float)mItems.get(i).getWidth();
            float height = (float)mItems.get(i).getHeight();
            canvas.drawRect(xPos, yPos, xPos+width, yPos+height, textpaint);
            canvas.drawRect(xPos+padding/2, yPos+padding/2, xPos+width-padding/2, yPos+height-padding/2, paint);
            canvas.drawText(mItems.get(i).getName(), xPos+padding, yPos+2*padding, textpaint);
            canvas.drawText("X", xPos+width-(float)1.5*padding, yPos+(float)1.5*padding, crossPaint);
        }
    }

    private float getMax(int choice){
        float max = Float.MIN_VALUE;
        for(int i=0; i<mItems.size(); i++){
            if(mItems.get(i).getxPos() > max){
                switch (choice) {
                    case 0:
                        max = mItems.get(i).getxPos();
                        break;
                    case 1:
                        max = mItems.get(i).getyPos();
                        break;
                    case 2:
                        max = mItems.get(i).getWidth();
                        break;
                    case 3:
                        max = mItems.get(i).getHeight();
                        break;
                }
            }
        }
        return max;
    }

}


package com.o7services.pillreminder.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.o7services.pillreminder.R;


@SuppressLint("AppCompatCustomView")
public class CustomCheckBox extends CheckBox {

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean t){
        if(t) {
            // checkbox_background is blue
            this.setBackgroundResource(R.drawable.checkbox_background);
            this.setTextColor(Color.BLUE);
        } else {
            this.setBackgroundColor(Color.TRANSPARENT);
            this.setTextColor(Color.BLACK);
        }
        super.setChecked(t);
    }
}
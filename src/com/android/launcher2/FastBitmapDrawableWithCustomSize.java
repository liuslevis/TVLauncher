/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// My change: CREATE this class based on FastBitmapDrawable. Used in Folder.java to create custom size icon in opened folder windows.

package com.android.launcher2;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;  
import android.graphics.Canvas;
import android.graphics.Matrix;  
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

class FastBitmapDrawableWithCustomSize extends Drawable {
    private Bitmap mBitmap;
    private int mAlpha;
    private int mWidth;
    private int mHeight;
    private final Paint mPaint = new Paint();

    // My change: ADD
    FastBitmapDrawableWithCustomSize(Bitmap b, int w, int h) {
	   mAlpha = 255;
        mBitmap = b;
        if (b != null && w>=0 && h>=0) {
            mWidth = w;
            mHeight = h;
             // calc zoom scale
            float scaleWidth = ((float)w / b.getWidth());  
            float scaleHeight = ((float)h / b.getHeight());
            // 创建操作图片用的Matrix对象
            Matrix matrix = new Matrix();
            // 设置缩放比例 
            matrix.postScale(scaleWidth, scaleHeight);        
            // 建立新的bitmap，其内容是对原bitmap的缩放后的图  
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, b.getWidth(), b.getHeight(), matrix, true);       
        } else {
            mWidth = mHeight = 0;

        }
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect r = getBounds();
        canvas.drawBitmap(mBitmap, r.left, r.top, mPaint);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
        mAlpha = alpha;
        mPaint.setAlpha(alpha);
    }

    public void setFilterBitmap(boolean filterBitmap) {
        mPaint.setFilterBitmap(filterBitmap);
    }

    public int getAlpha() {
        return mAlpha;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mHeight;
    }

    @Override
    public int getMinimumWidth() {
        return mWidth;
    }

    @Override
    public int getMinimumHeight() {
        return mHeight;
    }

    public void setBitmap(Bitmap b) {
        mBitmap = b;
        if (b != null) {
            mWidth = mBitmap.getWidth();
            mHeight = mBitmap.getHeight();
        } else {
            mWidth = mHeight = 0;
        }
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }
}

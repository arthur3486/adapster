/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arthurivanets.sample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.arthurivanets.sample.widget.markers.Roundable;

/**
 * Created by arthur3486
 */
public class TAFrameLayout extends FrameLayout implements Roundable {


    private float mTopLeftCornerRadius;
    private float mTopRightCornerRadius;
    private float mBottomLeftCornerRadius;
    private float mBottomRightCornerRadius;

    private RectF mRect;

    private Path mPath;




    public TAFrameLayout(Context context) {
        super(context);
        init();
    }




    public TAFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }




    public TAFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }




    @SuppressWarnings("NewApi")
    public TAFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }




    private void init() {
        mRect = new RectF();
        mPath = new Path();

        setCornerRadius(0f);
    }




    @Override
    public void setCornerRadius(float cornerRadius) {
        mTopLeftCornerRadius = cornerRadius;
        mTopRightCornerRadius = cornerRadius;
        mBottomLeftCornerRadius = cornerRadius;
        mBottomRightCornerRadius = cornerRadius;

        reconfigurePath();
        invalidate();
    }




    @Override
    public void setTopLeftCornerRadius(float topLeftCornerRadius) {
        mTopLeftCornerRadius = topLeftCornerRadius;

        reconfigurePath();
        invalidate();
    }




    @Override
    public float getTopLeftCornerRadius() {
        return mTopLeftCornerRadius;
    }




    @Override
    public void setTopRightCornerRadius(float topRightCornerRadius) {
        mTopRightCornerRadius = topRightCornerRadius;

        reconfigurePath();
        invalidate();
    }




    @Override
    public float getTopRightCornerRadius() {
        return mTopRightCornerRadius;
    }




    @Override
    public void setBottomLeftCornerRadius(float bottomLeftCornerRadius) {
        mBottomLeftCornerRadius = bottomLeftCornerRadius;

        reconfigurePath();
        invalidate();
    }




    @Override
    public float getBottomLeftCornerRadius() {
        return mBottomLeftCornerRadius;
    }




    @Override
    public void setBottomRightCornerRadius(float bottomRightCornerRadius) {
        mBottomRightCornerRadius = bottomRightCornerRadius;

        reconfigurePath();
        invalidate();
    }




    @Override
    public float getBottomRightCornerRadius() {
        return mBottomRightCornerRadius;
    }




    private float[] getCornerRadii() {
        return new float[] {
            mTopLeftCornerRadius,
            mTopLeftCornerRadius,
            mTopRightCornerRadius,
            mTopRightCornerRadius,
            mBottomRightCornerRadius,
            mBottomRightCornerRadius,
            mBottomLeftCornerRadius,
            mBottomLeftCornerRadius
        };
    }




    private boolean shouldRoundCorners() {
        return ((mTopLeftCornerRadius > 0)
                || (mTopRightCornerRadius > 0)
                || (mBottomLeftCornerRadius > 0)
                || (mBottomRightCornerRadius > 0));
    }




    @Override
    protected void onSizeChanged(int width,
                                 int height,
                                 int oldWidth,
                                 int oldHeight) {
        super.onSizeChanged(
            width,
            height,
            oldWidth,
            oldHeight
        );

        mRect.set(0, 0, width, height);
        reconfigurePath();
    }




    private void reconfigurePath() {
        mPath.reset();
        mPath.addRoundRect(mRect, getCornerRadii(), Path.Direction.CW);
        mPath.close();
    }




    @Override
    protected void dispatchDraw(Canvas canvas) {
        final int savedState = canvas.save();

        if(shouldRoundCorners()) {
            canvas.clipPath(mPath);
        }

        super.dispatchDraw(canvas);

        canvas.restoreToCount(savedState);
    }




}

/*
 * Copyright (C) 2015 Yaroslav Mytkalyk
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

package com.doctoror.aspectratiolayout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * {@link FrameLayout} with aspect ratio features
 */
public class AspectRatioLayout extends FrameLayout implements AspectRatioInterface {

    private final AspectRatioDelegate mDelegate;

    public AspectRatioLayout(final Context context) {
        super(context);
        mDelegate = new AspectRatioDelegate(this);
    }

    public AspectRatioLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mDelegate = new AspectRatioDelegate(this, attrs);
    }

    public AspectRatioLayout(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDelegate = new AspectRatioDelegate(this, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AspectRatioLayout(final Context context,
            final AttributeSet attrs,
            final int defStyleAttr,
            final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mDelegate = new AspectRatioDelegate(this, attrs);
    }

    @Override
    public void setAspect(final float aspect) {
        this.mDelegate.setAspect(aspect);
    }

    @Override
    public void setAspectType(final int aspectType) {
        this.mDelegate.setAspectType(aspectType);
    }

    @SuppressLint("WrongCall")
    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        this.mDelegate.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("WrongCall")
    @Override
    public void superOnMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

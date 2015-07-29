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

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

/**
 * Adds support of preserving aspect ratio to Views
 */
public final class AspectRatioDelegate {

    /**
     * The widget's height will be matched to width
     */
    public static final int ASPECT_VERTICAL = 0;

    /**
     * The widget's width will be matched to height
     */
    public static final int ASPECT_HORIZONTAL = 1;

    private final AspectRatioInterface mView;

    private float mAspect;

    private int mAspectType;

    public AspectRatioDelegate(@NonNull final AspectRatioInterface view) {
        this.mView = view;
        this.mAspect = -1;
    }

    public AspectRatioDelegate(@NonNull final AspectRatioInterface view,
            @Nullable final AttributeSet attrs) {
        this.mView = view;

        if (attrs != null) {
            final TypedArray arr = view.getContext()
                    .obtainStyledAttributes(attrs, R.styleable.AspectRatioInterface);
            if (arr != null) {
                setAspect(arr.getFloat(R.styleable.AspectRatioInterface_aspect, -1f));
                setAspectType(
                        arr.getInt(R.styleable.AspectRatioInterface_aspectType, ASPECT_VERTICAL));
                arr.recycle();
            }
        }
    }

    public void setAspect(final float aspect) {
        if (aspect < 0f) {
            throw new IllegalArgumentException("aspect must not be negative");
        }
        if (this.mAspect != aspect) {
            this.mAspect = aspect;
            this.mView.requestLayout();
        }
    }

    public void setAspectType(final int aspectType) {
        if (aspectType != ASPECT_VERTICAL && aspectType != ASPECT_HORIZONTAL) {
            throw new IllegalArgumentException("Invalid aspectType: " + aspectType);
        }
        if (this.mAspectType != aspectType) {
            this.mAspectType = aspectType;
            this.mView.requestLayout();
        }
    }

    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        this.mView.superOnMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mAspect != -1f) {

            final int mw = this.mView.getMeasuredWidth();
            final int mh = this.mView.getMeasuredHeight();

            switch (this.mAspectType) {
                case ASPECT_VERTICAL:
                    //this.setMeasuredDimension(mw, (int) (mw * this.aspect));

                    this.mView.superOnMeasure(MeasureSpec.makeMeasureSpec(mw, MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec((int) (mw * this.mAspect),
                                    MeasureSpec.EXACTLY));
                    break;

                case ASPECT_HORIZONTAL:
                    //this.setMeasuredDimension((int) (mh * this.aspect), mh);
                    this.mView.superOnMeasure(MeasureSpec
                                    .makeMeasureSpec((int) (mh * this.mAspect),
                                            MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(mh, MeasureSpec.EXACTLY));
                    break;

                default:
                    throw new IllegalArgumentException("Invalid aspect type: " + mAspectType);
            }
        }
    }
}
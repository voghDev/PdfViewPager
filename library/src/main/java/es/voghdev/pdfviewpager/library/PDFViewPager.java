/*
 * Copyright (C) 2016 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.pdfviewpager.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.pdfviewpager.library.adapter.LocalPosition;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;

public class PDFViewPager extends ViewPager {
    private PDFPagerAdapter pdfPagerAdapter;
    private List<OnPageChangeListener> onPdfChangeListeners;
    protected Context context;

    public PDFViewPager(Context context, String... pdfPaths) {
        super(context);
        this.context = context;
        init(pdfPaths);
    }

    public PDFViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    protected void init(String... pdfPaths) {
        initAdapter(context, pdfPaths);
    }

    protected void init(AttributeSet attrs) {
        if (isInEditMode()) {
            setBackgroundResource(R.drawable.flaticon_pdf_dummy);
            return;
        }

        if (attrs != null) {
            TypedArray a;

            a = context.obtainStyledAttributes(attrs, R.styleable.PDFViewPager);
            final String assetFileName = a.getString(R.styleable.PDFViewPager_assetFileName);

            if (assetFileName != null && assetFileName.length() > 0) {
                initAdapter(context, assetFileName);
            }

            a.recycle();
        }
    }

    protected void initAdapter(Context context, String... pdfPaths) {
        pdfPagerAdapter = new PDFPagerAdapter.Builder(context)
                .setPdfPaths(pdfPaths)
                .setOffScreenSize(getOffscreenPageLimit())
                .create();
        setAdapter(pdfPagerAdapter);
    }

    /**
     * PDFViewPager uses PhotoView, so this bugfix should be added
     * Issue explained in https://github.com/chrisbanes/PhotoView
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addOnPdfChangeListener(@NonNull final OnPdfChangeListener listener) {
        if (onPdfChangeListeners == null) {
            onPdfChangeListeners = new ArrayList<>();
        }
        OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
            private int prevPdfIndex = -1;

            @Override
            public void onPageSelected(int position) {
                listener.onPageSelected(position);
                LocalPosition localPosition = pdfPagerAdapter.getLocalPosition(position);
                if (localPosition.pdfIndex == prevPdfIndex) {
                    return;
                }

                prevPdfIndex = localPosition.pdfIndex;
                listener.onPdfSelected(localPosition.pdfIndex);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                listener.onPageScrollStateChanged(state);
            }
        };
        addOnPageChangeListener(onPageChangeListener);
        onPdfChangeListeners.add(onPageChangeListener);
    }

    public void clearOnPdfChangeListeners() {
        if (onPdfChangeListeners != null) {
            for (OnPageChangeListener onPageChangeListener : onPdfChangeListeners) {
                removeOnPageChangeListener(onPageChangeListener);
            }
            onPdfChangeListeners.clear();
        }
    }

    public View findViewAtLocalPosition(LocalPosition localPosition) {
        int globalPosition = pdfPagerAdapter.getGlobalPosition(localPosition);
        return findViewAtGlobalPosition(globalPosition);
    }

    public View findViewAtGlobalPosition(int position) {
        String tag = pdfPagerAdapter.getTag(position);
        return findViewWithTag(tag);
    }

    public static abstract class OnPdfChangeListener extends SimpleOnPageChangeListener {
        public abstract void onPdfSelected(int position);
    }
}

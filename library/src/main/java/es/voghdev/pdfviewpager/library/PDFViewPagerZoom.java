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
import android.util.AttributeSet;
import android.view.MotionEvent;

import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.adapter.PdfScale;

public class PDFViewPagerZoom extends PDFViewPager {
    public PDFViewPagerZoom(Context context, String pdfPath) {
        super(context, pdfPath);
    }

    public PDFViewPagerZoom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void initAdapter(Context context, String pdfPath) {
        setAdapter(new PDFPagerAdapter.Builder(context)
                .setPdfPath(pdfPath)
                .setOffScreenSize(getOffscreenPageLimit())
                .create()
        );
    }

    protected void init(AttributeSet attrs) {
        if (isInEditMode()) {
            setBackgroundResource(R.drawable.flaticon_pdf_dummy);
            return;
        }

        if (attrs != null) {
            TypedArray a;

            a = context.obtainStyledAttributes(attrs, R.styleable.PDFViewPager);
            String assetFileName = a.getString(R.styleable.PDFViewPager_assetFileName);
            float scale = a.getFloat(R.styleable.PDFViewPager_scale, PdfScale.DEFAULT_SCALE);

            if (assetFileName != null && assetFileName.length() > 0) {
                setAdapter(new PDFPagerAdapter.Builder(context)
                        .setPdfPath(assetFileName)
                        .setScale(scale)
                        .setOffScreenSize(getOffscreenPageLimit())
                        .create());
            }

            a.recycle();
        }
    }

    /**
     * Bugfix explained in https://github.com/chrisbanes/PhotoView
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
}

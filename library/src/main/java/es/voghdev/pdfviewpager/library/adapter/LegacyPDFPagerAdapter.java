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
package es.voghdev.pdfviewpager.library.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import es.voghdev.pdfviewpager.library.R;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Deprecated class. We recommend to use PDFPagerAdapter instead
 */
@Deprecated
public class LegacyPDFPagerAdapter extends BasePDFPagerAdapter {
    public LegacyPDFPagerAdapter(Context context, String pdfPath) {
        super(context, pdfPath);
    }

    public LegacyPDFPagerAdapter(Context context, String pdfPath, int offScreenSize) {
        super(context, pdfPath, offScreenSize);
    }

    @Override
    @SuppressWarnings("NewApi")
    public Object instantiateItem(ViewGroup container, int position) {
        View v = inflater.inflate(R.layout.view_zoomable_pdf_page, container, false);
        ImageViewTouch ivt = (ImageViewTouch) v.findViewById(R.id.imageViewZoom);

        if(renderer == null || getCount() < position)
            return v;

        PdfRenderer.Page page = getPDFPage(renderer, position);

        Bitmap bitmap = bitmapContainer.get(position);
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();

        ivt.setImageBitmap(bitmap);
        ((ViewPager) container).addView(v, 0);

        return v;
    }
}

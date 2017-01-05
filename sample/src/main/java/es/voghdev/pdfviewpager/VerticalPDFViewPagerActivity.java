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
package es.voghdev.pdfviewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import es.voghdev.pdfviewpager.library.VerticalPDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.BasePDFPagerAdapter;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;

public class VerticalPDFViewPagerActivity extends BaseSampleActivity {
    VerticalPDFViewPager pdfViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vertical_pdf_view_pager);

        setTitle("Vertical PDF View Pager");

        configureVerticalPdfViewPager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ((BasePDFPagerAdapter) pdfViewPager.getAdapter()).close();
    }

    protected void configureVerticalPdfViewPager() {
        pdfViewPager = (VerticalPDFViewPager) findViewById(R.id.verticalPdfViewPager);
        pdfViewPager.setAdapter(new PDFPagerAdapter.Builder(this)
                .setPdfPath(getPdfPathOnSDCard())
                .create());
    }

    public static void open(Context ctx) {
        Intent intent = new Intent(ctx, VerticalPDFViewPagerActivity.class);
        ctx.startActivity(intent);
    }
}

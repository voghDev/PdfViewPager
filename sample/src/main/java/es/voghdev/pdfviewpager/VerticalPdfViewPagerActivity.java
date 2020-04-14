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

import es.voghdev.pdfviewpager.library.adapter.BasePDFPagerAdapter;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.util.VerticalViewPager;

public class VerticalPdfViewPagerActivity extends BaseSampleActivity {
    private VerticalViewPager verticalPdfViewPager;
    private PDFPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.vertical_pdf_view_pager_example));
        setContentView(R.layout.activity_vertical_view_pager);

        verticalPdfViewPager = findViewById(R.id.verticalPdfViewPager);

        adapter = new PDFPagerAdapter(this, "moby.pdf");
        verticalPdfViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ((BasePDFPagerAdapter) verticalPdfViewPager.getAdapter()).close();
    }

    public static void open(Context context) {
        Intent i = new Intent(context, VerticalPdfViewPagerActivity.class);
        context.startActivity(i);
    }
}
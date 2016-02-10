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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;

/**
 * Created by olmo on 9/02/16.
 */
public class AssetOnXMLActivity extends Activity
{
    PDFViewPager pdfViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_on_xml);

        pdfViewPager = (PDFViewPager) findViewById(R.id.pdfViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ((PDFPagerAdapter)pdfViewPager.getAdapter()).close();
    }

    public static void open(Context context){
        Intent i = new Intent(context, AssetOnXMLActivity.class);
        context.startActivity(i);
    }
}
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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;

public class MainActivity extends AppCompatActivity {
    PDFViewPager pdfViewPager;
    PDFPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle(R.string.std_example);
        setContentView(R.layout.activity_main);

        pdfViewPager = (PDFViewPager) findViewById(R.id.pdfViewPager);

        adapter = new PDFPagerAdapter(this, "sample.pdf");
        pdfViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(adapter != null) {
            adapter.close();
            adapter = null;
        }
    }

    // region OnClick handlers
    public void onClickSample2(View v){
        RemotePDFActivity.open(this);
    }

    public void onClickSample3(View v){
        AssetOnSDActivity.open(this);
    }

    public void onClickSample4(View v){
        Toast.makeText(this, R.string.dummy_msg, Toast.LENGTH_LONG).show();
    }

    public void onClickSample5(View v){
        AssetOnXMLActivity.open(this);
        ZoomablePDFOnXMLActivity.open(this);
    }

    public void onClickSample6(View v){
        LegacyPDFActivity.open(this);
    }

    public void onClickSample7(View v){
        ZoomablePDFActivityIVZoom.open(this);
    }

    public void onClickSample8(View v){
        ZoomablePDFActivityPhotoView.open(this);
    }
    // endregion
}

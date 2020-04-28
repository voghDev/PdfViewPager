package es.voghdev.pdfviewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.adapter.PdfErrorHandler;

import static android.view.View.VISIBLE;

public class InvalidPdfActivity extends BaseSampleActivity {
    final String invalidPdfPath = "https://live.staticflickr.com/4561/38054606355_26429c884f_b.jpg";

    PDFViewPager pdfViewPager;
    View pdfErrorView;
    PDFPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invalid_pdf);

        pdfViewPager = findViewById(R.id.pdfViewPager);
        pdfErrorView = findViewById(R.id.pdfErrorView);

        adapter = new PDFPagerAdapter.Builder(this)
                .setErrorHandler(new PdfErrorHandler() {
                    @Override
                    public void onPdfError(Throwable t) {
                        pdfErrorView.setVisibility(VISIBLE);
                    }
                })
                .setPdfPath(invalidPdfPath)
                .create();

        pdfViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        adapter.close();
    }
}

package es.voghdev.pdfviewpager.library;

import android.content.Context;
import android.util.AttributeSet;

import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapterZoom;

public class PDFViewPagerZoom extends PDFViewPager {
    public PDFViewPagerZoom(Context context, String pdfPath) {
        super(context, pdfPath);
    }

    public PDFViewPagerZoom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void init(String pdfPath) {
        setAdapter(new PDFPagerAdapterZoom(context, pdfPath));
    }
}

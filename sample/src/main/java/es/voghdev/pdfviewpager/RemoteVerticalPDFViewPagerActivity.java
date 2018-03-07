package es.voghdev.pdfviewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import es.voghdev.pdfviewpager.library.RemoteVerticalPDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.BasePDFPagerAdapter;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * Created by Nikita on 07.03.2018.
 */

public class RemoteVerticalPDFViewPagerActivity extends BaseSampleActivity {

    RemoteVerticalPDFViewPager pdfViewPager;

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
        pdfViewPager = new RemoteVerticalPDFViewPager(this, getString(R.string.sample_pdf_url), new DownloadFile.Listener() {
            @Override
            public void onSuccess(String url, String destinationPath) {
                String file = FileUtil.extractFileNameFromURL(url);
                pdfViewPager.setAdapter(new PDFPagerAdapter.Builder(RemoteVerticalPDFViewPagerActivity.this)
                        .setPdfPath(file)
                        .create());
                setContentView(pdfViewPager);
            }

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onProgressUpdate(int progress, int total) {

            }
        });
    }

    public static void open(Context ctx) {
        Intent intent = new Intent(ctx, VerticalPDFViewPagerActivity.class);
        ctx.startActivity(intent);
    }

}

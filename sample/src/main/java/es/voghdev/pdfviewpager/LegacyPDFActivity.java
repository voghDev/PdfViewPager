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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.remote.DownloadFileUrlConnectionImpl;

/**
 * Sample Activity for API Levels under 21
 */
public class LegacyPDFActivity extends BaseSampleActivity implements DownloadFile.Listener {
    Button button;
    ProgressBar progressBar;
    TextView textView;
    DownloadFile downloadFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.legacy_pdf);
        setContentView(R.layout.activity_legacy);

        bindViews();

        downloadFile = new DownloadFileUrlConnectionImpl(this, new Handler(), this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.INVISIBLE);
                downloadFile.download(getString(R.string.sample_pdf_url),
                        new File(getExternalFilesDir("pdf"), "legacy.pdf").getAbsolutePath());
                textView.setText(R.string.downloading);
            }
        });

    }

    private void bindViews() {
        button = (Button) findViewById(R.id.btn_download);
        textView = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public static void open(Context context) {
        Intent i = new Intent(context, LegacyPDFActivity.class);
        context.startActivity(i);
    }

    private void launchOpenPDFIntent(String destinationPath) {
        File file = new File(destinationPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        button.setVisibility(View.VISIBLE);
        progressBar.setProgress(100);
        textView.setText(R.string.downloaded_wait);
        launchOpenPDFIntent(destinationPath);
    }

    @Override
    public void onFailure(Exception e) {
        button.setVisibility(View.INVISIBLE);
        progressBar.setProgress(0);
        textView.setText(String.format("Download error: %s", e.getMessage()));
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        if (progressBar.getMax() != total) {
            progressBar.setMax(total);
        }
        progressBar.setProgress(progress);
    }
}
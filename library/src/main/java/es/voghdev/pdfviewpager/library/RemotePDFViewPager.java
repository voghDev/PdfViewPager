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
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.io.File;

import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.remote.DownloadFileUrlConnectionImpl;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class RemotePDFViewPager extends ViewPager implements DownloadFile.Listener {
    protected Context context;
    protected DownloadFile.Listener listener;

    public RemotePDFViewPager(Context context, String pdfUrl, DownloadFile.Listener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        init(pdfUrl);
    }

    public RemotePDFViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(String pdfUrl) {
        DownloadFile downloadFile = new DownloadFileUrlConnectionImpl(context, new Handler(), this);
        downloadFile.download(pdfUrl, new File(context.getCacheDir(), FileUtil.extractFileNameFromURL(pdfUrl)).getAbsolutePath());
    }

    private void init(AttributeSet attrs){
        if(attrs != null){
            TypedArray a;

            a = context.obtainStyledAttributes(attrs, R.styleable.PDFViewPager);
            String pdfUrl = a.getString(R.styleable.PDFViewPager_pdfUrl);

            if( pdfUrl != null && pdfUrl.length() > 0)
                init(pdfUrl);
        }
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        listener.onSuccess(url, destinationPath);
    }

    @Override
    public void onFailure(Exception e) {
        listener.onFailure(e);
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        listener.onProgressUpdate(progress, total);
    }

    public class NullListener implements DownloadFile.Listener{
        public void onSuccess(String url, String destinationPath) {}
        public void onFailure(Exception e) {}
        public void onProgressUpdate(int progress, int total) {}
    }
}

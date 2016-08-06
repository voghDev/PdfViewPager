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
package es.voghdev.pdfviewpager.library.asset;

import android.content.Context;
import android.os.Handler;

import java.io.IOException;

import es.voghdev.pdfviewpager.library.util.FileUtil;

public class CopyAssetThreadImpl implements CopyAsset {
    Context context;
    Handler uiThread;
    CopyAsset.Listener listener = new NullListener();

    public CopyAssetThreadImpl(Context context, Handler uiThread, Listener listener) {
        this.context = context;
        this.uiThread = uiThread;
        if (listener != null) {
            this.listener = listener;
        }
    }

    public CopyAssetThreadImpl(Context context, Handler uiThread) {
        this.context = context;
        this.uiThread = uiThread;
    }

    @Override
    public void copy(final String assetName, final String destinationPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileUtil.copyAsset(context, assetName, destinationPath);
                    notifySuccess(assetName, destinationPath);
                } catch (IOException e) {
                    notifyError(e);
                }
            }
        }).start();
    }

    private void notifySuccess(final String assetName, final String destinationPath) {
        if (uiThread == null) {
            return;
        }

        uiThread.post(new Runnable() {
            @Override
            public void run() {
                listener.success(assetName, destinationPath);
            }
        });
    }

    private void notifyError(final IOException e) {
        if (uiThread == null) {
            return;
        }

        uiThread.post(new Runnable() {
            @Override
            public void run() {
                listener.failure(e);
            }
        });
    }

    protected class NullListener implements Listener {
        public void success(String assetName, String destinationPath) {
        }

        public void failure(Exception e) {
        }
    }
}

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

import android.app.Application;
import android.os.Handler;

import java.io.File;

import es.voghdev.pdfviewpager.library.asset.CopyAsset;
import es.voghdev.pdfviewpager.library.asset.CopyAssetThreadImpl;

public class SampleApplication extends Application {
    final String[] sampleAssets = {"adobe.pdf", "sample.pdf", "moby.pdf"};

    @Override
    public void onCreate() {
        super.onCreate();

        initSampleAssets();
    }

    private void initSampleAssets() {
        CopyAsset copyAsset = new CopyAssetThreadImpl(this, new Handler());
        for (String asset : sampleAssets) {
            copyAsset.copy(asset, new File(getCacheDir(), asset).getAbsolutePath());
        }
    }
}

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
package es.voghdev.pdfviewpager.library.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import es.voghdev.pdfviewpager.library.BuildConfig;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class CopyAssetService extends IntentService {
    private static final String ACTION_COPY_ASSET = BuildConfig.APPLICATION_ID + ".copy_asset";

    private static final String EXTRA_ASSET = BuildConfig.APPLICATION_ID + ".asset";
    private static final String EXTRA_DESTINATION = BuildConfig.APPLICATION_ID + ".destination_path";

    public CopyAssetService() {
        super("CopyAssetService");
    }

    public static void startCopyAction(Context context, String asset, String destinationPath) {
        Intent intent = new Intent(context, CopyAssetService.class);
        intent.setAction(ACTION_COPY_ASSET);
        intent.putExtra(EXTRA_ASSET, asset);
        intent.putExtra(EXTRA_DESTINATION, destinationPath);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_COPY_ASSET.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_ASSET);
                final String param2 = intent.getStringExtra(EXTRA_DESTINATION);
                handleActionCopyAsset(param1, param2);
            }
        }
    }

    private void handleActionCopyAsset(String asset, String destinationPath) {
        try {
            FileUtil.copyAsset(this, asset, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

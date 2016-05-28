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
package es.voghdev.pdfviewpager.library.adapter;

import android.graphics.Bitmap;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

@Deprecated
public class DefaultBitmapContainer implements BitmapContainer {
    SparseArray<WeakReference<Bitmap>> bitmaps;
    private int width;
    private int height;
    private Bitmap.Config config;

    public DefaultBitmapContainer(int width, int height, Bitmap.Config config) {
        this.width = width;
        this.height = height;
        this.config = config;
        bitmaps = new SparseArray<>();
    }

    @Override
    public Bitmap get(int position) {
        if(bitmaps.get(position) == null)
            bitmaps.put(position, new WeakReference<Bitmap>(createNewBitmap(width, height, config)));

        return bitmaps.get(position).get();
    }

    @Override
    public void remove(int position) {
        recycleBitmap(position);
    }

    @Override
    public void clear() {
        for(int i=0; bitmaps != null && i<bitmaps.size(); ++i) {
            recycleBitmap(bitmaps.keyAt(i));
        }
        bitmaps.clear();
    }

    protected void recycleBitmap(int position){
        Bitmap b = bitmaps.get(position).get();
        if(b != null && !b.isRecycled()) {
            b.recycle();
            bitmaps.remove(position);
        }
    }

    protected Bitmap createNewBitmap(int width, int height, Bitmap.Config config) {
        return Bitmap.createBitmap(width, height, config);
    }
}

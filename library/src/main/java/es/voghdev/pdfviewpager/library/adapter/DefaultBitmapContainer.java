package es.voghdev.pdfviewpager.library.adapter;

import android.graphics.Bitmap;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

public class DefaultBitmapContainer implements BitmapContainer {
    SparseArray<WeakReference<Bitmap>> bitmaps;

    public DefaultBitmapContainer() {
        bitmaps = new SparseArray<>();
    }

    @Override
    public void put(int position, Bitmap bitmap) {
        bitmaps.put(position, new WeakReference<Bitmap>(bitmap));
    }

    @Override
    public Bitmap get(int position) {
        return null;
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
}

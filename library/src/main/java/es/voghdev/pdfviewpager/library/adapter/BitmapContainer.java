package es.voghdev.pdfviewpager.library.adapter;

import android.graphics.Bitmap;

public interface BitmapContainer {
    public Bitmap get(int position);
    public void remove(int position);
    public void clear();
}
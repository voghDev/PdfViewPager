package es.voghdev.pdfviewpager.library.subscaleview.decoder;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@SuppressWarnings("WeakerAccess")
public class CompatDecoderFactory<T> implements DecoderFactory<T> {

    private final Class<? extends T> clazz;
    private final Bitmap.Config bitmapConfig;


    public CompatDecoderFactory(@NonNull Class<? extends T> clazz) {
        this(clazz, null);
    }


    public CompatDecoderFactory(@NonNull Class<? extends T> clazz, Bitmap.Config bitmapConfig) {
        this.clazz = clazz;
        this.bitmapConfig = bitmapConfig;
    }

    @Override
    @NonNull
    public T make() throws
            IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        if (bitmapConfig == null) {
            return clazz.newInstance();
        } else {
            Constructor<? extends T> ctor = clazz.getConstructor(Bitmap.Config.class);
            return ctor.newInstance(bitmapConfig);
        }
    }

}
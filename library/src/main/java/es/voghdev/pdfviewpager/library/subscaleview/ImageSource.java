package es.voghdev.pdfviewpager.library.subscaleview;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@SuppressWarnings({"unused", "WeakerAccess"})
public final class ImageSource {

    static final String FILE_SCHEME = "file:///";
    static final String ASSET_SCHEME = "file:///android_asset/";

    private final Uri uri;
    private final Bitmap bitmap;
    private final Integer resource;
    private boolean tile;
    private int sWidth;
    private int sHeight;
    private Rect sRegion;
    private boolean cached;

    private ImageSource(Bitmap bitmap, boolean cached) {
        this.bitmap = bitmap;
        this.uri = null;
        this.resource = null;
        this.tile = false;
        this.sWidth = bitmap.getWidth();
        this.sHeight = bitmap.getHeight();
        this.cached = cached;
    }

    private ImageSource(@NonNull Uri uri) {
        // #114 If file doesn't exist, attempt to url decode the URI and try again
        String uriString = uri.toString();
        if (uriString.startsWith(FILE_SCHEME)) {
            File uriFile = new File(uriString.substring(FILE_SCHEME.length() - 1));
            if (!uriFile.exists()) {
                try {
                    uri = Uri.parse(URLDecoder.decode(uriString, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    // Fallback to encoded URI. This exception is not expected.
                }
            }
        }
        this.bitmap = null;
        this.uri = uri;
        this.resource = null;
        this.tile = true;
    }

    private ImageSource(int resource) {
        this.bitmap = null;
        this.uri = null;
        this.resource = resource;
        this.tile = true;
    }

    @NonNull
    public static ImageSource resource(int resId) {
        return new ImageSource(resId);
    }

    @NonNull
    public static ImageSource asset(@NonNull String assetName) {
        //noinspection ConstantConditions
        if (assetName == null) {
            throw new NullPointerException("Asset name must not be null");
        }
        return uri(ASSET_SCHEME + assetName);
    }

    @NonNull
    public static ImageSource uri(@NonNull String uri) {
        //noinspection ConstantConditions
        if (uri == null) {
            throw new NullPointerException("Uri must not be null");
        }
        if (!uri.contains("://")) {
            if (uri.startsWith("/")) {
                uri = uri.substring(1);
            }
            uri = FILE_SCHEME + uri;
        }
        return new ImageSource(Uri.parse(uri));
    }

    @NonNull
    public static ImageSource uri(@NonNull Uri uri) {
        //noinspection ConstantConditions
        if (uri == null) {
            throw new NullPointerException("Uri must not be null");
        }
        return new ImageSource(uri);
    }

    @NonNull
    public static ImageSource bitmap(@NonNull Bitmap bitmap) {
        //noinspection ConstantConditions
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        }
        return new ImageSource(bitmap, false);
    }

    @NonNull
    public static ImageSource cachedBitmap(@NonNull Bitmap bitmap) {
        //noinspection ConstantConditions
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        }
        return new ImageSource(bitmap, true);
    }

    @NonNull
    public ImageSource tilingEnabled() {
        return tiling(true);
    }

    @NonNull
    public ImageSource tilingDisabled() {
        return tiling(false);
    }

    @NonNull
    public ImageSource tiling(boolean tile) {
        this.tile = tile;
        return this;
    }

    @NonNull
    public ImageSource region(Rect sRegion) {
        this.sRegion = sRegion;
        setInvariants();
        return this;
    }

    @NonNull
    public ImageSource dimensions(int sWidth, int sHeight) {
        if (bitmap == null) {
            this.sWidth = sWidth;
            this.sHeight = sHeight;
        }
        setInvariants();
        return this;
    }

    private void setInvariants() {
        if (this.sRegion != null) {
            this.tile = true;
            this.sWidth = this.sRegion.width();
            this.sHeight = this.sRegion.height();
        }
    }

    protected Uri getUri() {
        return uri;
    }

    protected Bitmap getBitmap() {
        return bitmap;
    }

    protected Integer getResource() {
        return resource;
    }

    protected boolean getTile() {
        return tile;
    }

    protected int getSWidth() {
        return sWidth;
    }

    protected int getSHeight() {
        return sHeight;
    }

    protected Rect getSRegion() {
        return sRegion;
    }

    protected boolean isCached() {
        return cached;
    }
}
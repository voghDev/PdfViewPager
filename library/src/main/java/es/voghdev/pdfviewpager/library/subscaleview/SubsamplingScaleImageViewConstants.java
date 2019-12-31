package es.voghdev.pdfviewpager.library.subscaleview;

import java.util.Arrays;
import java.util.List;

public class SubsamplingScaleImageViewConstants {
    public static final String TAG = SubsamplingScaleImageView.class.getSimpleName();

    public static final int ORIENTATION_USE_EXIF = -1;
    public static final int ORIENTATION_0 = 0;
    public static final int ORIENTATION_90 = 90;
    public static final int ORIENTATION_180 = 180;
    public static final int ORIENTATION_270 = 270;

    public static final List<Integer> VALID_ORIENTATIONS =
            Arrays.asList(ORIENTATION_0, ORIENTATION_90, ORIENTATION_180, ORIENTATION_270, ORIENTATION_USE_EXIF);

    public static final int ZOOM_FOCUS_FIXED = 1;
    public static final int ZOOM_FOCUS_CENTER = 2;
    public static final int ZOOM_FOCUS_CENTER_IMMEDIATE = 3;

    public static final List<Integer> VALID_ZOOM_STYLES =
            Arrays.asList(ZOOM_FOCUS_FIXED, ZOOM_FOCUS_CENTER, ZOOM_FOCUS_CENTER_IMMEDIATE);

    public static final int EASE_OUT_QUAD = 1;
    public static final int EASE_IN_OUT_QUAD = 2;

    public static final List<Integer> VALID_EASING_STYLES =
            Arrays.asList(EASE_IN_OUT_QUAD, EASE_OUT_QUAD);

    public static final int PAN_LIMIT_INSIDE = 1;
    public static final int PAN_LIMIT_OUTSIDE = 2;
    public static final int PAN_LIMIT_CENTER = 3;

    public static final List<Integer> VALID_PAN_LIMITS =
            Arrays.asList(PAN_LIMIT_INSIDE, PAN_LIMIT_OUTSIDE, PAN_LIMIT_CENTER);

    public static final int SCALE_TYPE_CENTER_INSIDE = 1;
    public static final int SCALE_TYPE_CENTER_CROP = 2;
    public static final int SCALE_TYPE_CUSTOM = 3;
    public static final int SCALE_TYPE_START = 4;

    public static final List<Integer> VALID_SCALE_TYPES =
            Arrays.asList(SCALE_TYPE_CENTER_CROP, SCALE_TYPE_CENTER_INSIDE, SCALE_TYPE_CUSTOM, SCALE_TYPE_START);

    public static final int ORIGIN_ANIM = 1;
    public static final int ORIGIN_TOUCH = 2;
    public static final int ORIGIN_FLING = 3;
    public static final int ORIGIN_DOUBLE_TAP_ZOOM = 4;
}

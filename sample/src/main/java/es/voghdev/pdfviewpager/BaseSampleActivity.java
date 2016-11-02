package es.voghdev.pdfviewpager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by olmo on 2/11/16.
 */

public class BaseSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configureActionBar();
    }

    protected void configureActionBar() {
        int color = ContextCompat.getColor(this, R.color.pdfViewPager_ab_color);
        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(color));
    }
}

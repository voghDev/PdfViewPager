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

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import es.voghdev.pdfviewpager.library.view.VerticalViewPager;

public class VerticalViewPagerActivity extends BaseSampleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vertical_view_pager);

        setTitle("Vertical View Pager");

        configureVerticalViewPager();
    }

    protected void configureVerticalViewPager() {
        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalViewPager);
        SampleAdapter adapter = new SampleAdapter(this);
        verticalViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void open(Context ctx) {
        Intent intent = new Intent(ctx, VerticalViewPagerActivity.class);
        ctx.startActivity(intent);
    }

    protected class SampleAdapter extends PagerAdapter {
        final int[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN};
        Context context;

        public SampleAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (View) object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = new View(context);
            v.setBackgroundColor(colors[position]);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }

}

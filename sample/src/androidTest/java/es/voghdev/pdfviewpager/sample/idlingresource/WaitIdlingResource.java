package es.voghdev.pdfviewpager.sample.idlingresource;

import android.support.test.espresso.IdlingResource;

/**
 * Created by olmo on 7/03/16.
 */
public class WaitIdlingResource implements IdlingResource {
    long startMillisecs;
    long waitTime;
    ResourceCallback resourceCallback;

    public WaitIdlingResource(long startMillisecs, long waitTime) {
        this.startMillisecs = startMillisecs;
        this.waitTime = waitTime;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        long ellapsed = (System.currentTimeMillis() - startMillisecs);
        if(ellapsed > waitTime)
            resourceCallback.onTransitionToIdle();
        return ellapsed > waitTime;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}

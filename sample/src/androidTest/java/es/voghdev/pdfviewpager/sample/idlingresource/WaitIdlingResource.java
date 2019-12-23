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
package es.voghdev.pdfviewpager.sample.idlingresource;

import androidx.test.espresso.IdlingResource;

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
        if (ellapsed > waitTime) {
            resourceCallback.onTransitionToIdle();
        }
        return ellapsed > waitTime;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}

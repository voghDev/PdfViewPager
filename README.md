# PdfViewPager

[![Download](https://api.bintray.com/packages/voghdev/maven/pdfviewpager/images/download.svg) ](https://bintray.com/voghdev/maven/pdfviewpager/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PdfViewPager-green.svg?style=true)](https://android-arsenal.com/details/1/3155)
[![Build Status](https://travis-ci.org/voghDev/PdfViewPager.svg?branch=master)](https://travis-ci.org/voghDev/PdfViewPager)

Android widget to display PDF documents in your Activities or Fragments.

Important note: **PDFViewPager** uses [PdfRenderer][6] class, which works **only on API 21 or higher**.
See [Official doc][6] for details.

If you are targeting pre-Lollipop devices, have a look at the [legacy sample][7]

Installation
------------

Add this line in your *app/build.gradle*

    compile 'es.voghdev.pdfviewpager:library:1.0.4'

Usage
-----

Use **PDFViewPager** class to load PDF files from assets or SD card

![Screenshot][localPDFScreenshot] ![Screenshot][zoomingScreenshot]

1.- Copy your assets to cache directory if your PDF is located on assets directory

    CopyAsset copyAsset = new CopyAssetThreadImpl(context, new Handler());
    copyAsset.copy(asset, new File(getCacheDir(), "sample.pdf").getAbsolutePath());

2a.- Create your **PDFViewPager** passing your PDF file, located in *assets* (see [sample][8])

    pdfViewPager = new PDFViewPager(this, "sample.pdf");

2b.- Or directly, declare it on your XML layout

    <es.voghdev.pdfviewpager.library.PDFViewPager
        android:id="@+id/pdfViewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:assetFileName="sample.pdf"/>

It will automatically have zooming and panning capability

3.- Release adapter in *onDestroy*

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ((PDFPagerAdapter) pdfViewPager.getAdapter()).close();
    }

PDF's on SD card
----------------

1.- Create a **PDFViewPager** object, passing the file location in your SD card

    PDFViewPager pdfViewPager = new PDFViewPager(context, getPdfPathOnSDCard());

    protected String getPdfPathOnSDCard() {
        File f = new File(getExternalFilesDir("pdf"), "adobe.pdf");
        return f.getAbsolutePath();
    }

2.- Don't forget to release the adapter in *onDestroy*

        @Override
        protected void onDestroy() {
            super.onDestroy();

            ((PDFPagerAdapter) pdfViewPager.getAdapter()).close();
        }

Remote PDF's from a URL
-----------------------

![Screenshot][remotePDFScreenshot]

1.- Add `INTERNET`, `READ_EXTERNAL_STORAGE` and `WRITE_EXTERNAL_STORAGE` permissions on your AndroidManifest.xml

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

2.- Make your Activity or Fragment implement DownloadFile.Listener

    public class RemotePDFActivity extends AppCompatActivity implements DownloadFile.Listener {

3.- Create a **RemotePDFViewPager** object

    String url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";
    
    RemotePDFViewPager remotePDFViewPager =
          new RemotePDFViewPager(context, url, this);

4.- Configure the corresponding callbacks and they will be called on each situation.

    @Override
    public void onSuccess(String url, String destinationPath) {
        // That's the positive case. PDF Download went fine

        adapter = new PDFPagerAdapter(this, "AdobeXMLFormsSamples.pdf");
        remotePDFViewPager.setAdapter(adapter);
        setContentView(remotePDFViewPager);
    }

    @Override
    public void onFailure(Exception e) {
        // This will be called if download fails
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        // You will get download progress here
        // Always on UI Thread so feel free to update your views here
    }

5.- Don't forget to close adapter in *onDestroy* to release all resources

    @Override
    protected void onDestroy() {
        super.onDestroy();

        adapter.close();
    }

Usage in Kotlin
---------------

As you might figure out, the library is fully usable in Kotlin programming language. You can find example code [here][12].

Just import the library as a gradle dependency as you would do in Java.

TODOs
-----

- [X] Make initial Pdf scale setable by code *(requested by various users on issues)*
- [X] Load PDF documents from SD card
- [X] Make PDF documents zoomable with pinch and double tap (two approaches, [ImageViewZoom][5] and [photoview][11])
- [X] Unify all features in only one **PDFViewPager** and **PDFPagerAdapter** class
- [X] Support API Levels under 21, by downloading PDF and invoking system native intent.
- [X] UI tests
- [X] Add checkstyle, refactor & improve code quality
- [ ] Boost PDF opening & rendering performance using an asnychronous load

See [changelog][4] for details

Developed By
------------

* Olmo Gallegos Hernández - [voghDev][9] - [mobiledevstories.com][10]

<a href="http://twitter.com/voghDev">
  <img alt="Follow me on Twitter" src="https://image.freepik.com/iconos-gratis/twitter-logo_318-40209.jpg" height="60" width="60" />
</a>
<a href="https://www.linkedin.com/profile/view?id=91543271">
  <img alt="Find me on Linkedin" src="https://image.freepik.com/iconos-gratis/boton-del-logotipo-linkedin_318-84979.png" height="60" width="60" />
</a>

# License

    Copyright 2016 Olmo Gallegos Hernández

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Contributing
------------

**For noobs (like me some months ago)**

    fork the project into your GitHub account
    now clone your GitHub repo for this project
    implement your changes
    commit your changes, push them into your repo
    review your code and send me a pull request if you consider it

**For not-so-noobs**

Please make sure that your changes pass both checkstyle and UI tests before submitting them

    ./gradlew checkstyle

    ./gradlew test

And with your Android device connected

    ./gradlew connectedCheck

[remotePDFScreenshot]: ./screenshots/remote.gif
[localPDFScreenshot]: ./screenshots/local.gif
[sdcardPDFScreenshot]: ./screenshots/sdcard.gif
[zoomingScreenshot]: ./screenshots/zooming.gif
[4]: https://github.com/voghDev/PdfViewPager/blob/master/CHANGELOG.md
[5]: https://github.com/sephiroth74/ImageViewZoom
[6]: http://developer.android.com/reference/android/graphics/pdf/PdfRenderer.html
[7]: https://github.com/voghDev/PdfViewPager/blob/master/sample/src/main/java/es/voghdev/pdfviewpager/LegacyPDFActivity.java
[8]: https://github.com/voghDev/PdfViewPager/tree/master/sample/src/main/java/es/voghdev/pdfviewpager
[9]: https://github.com/voghDev
[10]: http://www.mobiledevstories.com
[11]: https://github.com/chrisbanes/PhotoView
[12]: https://github.com/voghDev/HelloKotlin/blob/pdfviewpager/app/src/main/java/es/voghdev/hellokotlin/PdfViewPagerActivity.kt

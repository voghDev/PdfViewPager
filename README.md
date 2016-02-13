# PdfViewPager

Android widget to display PDF documents in your Activities or Fragments.

Installation
------------

hopefully, the library will be available soon as a gradle dependency.
In the meanwhile, you can checkout the source.

Usage - Remote PDF's
--------------------

![Screenshot][remotePDFScreenshot]

Use **RemotePDFViewPager** to load from remote URLs

1.- Make your Activity or Fragment implement DownloadFile.Listener

    public class RemotePDFActivity extends AppCompatActivity implements DownloadFile.Listener {

2.- Create a **RemotePDFViewPager** object

    RemotePDFViewPager remotePDFViewPager =
          new RemotePDFViewPager(context, "http://partners.adobe.com/public/developer/en/xml/AdobeXMLFormsSamples.pdf", this);

3.- Configure these callbacks and they will be called on the each situation.

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
    }

4.- Don't forget to close adapter in *onDestroy* to release all resources

    @Override
    protected void onDestroy() {
        super.onDestroy();

        adapter.close();
    }

Usage - Local PDF's
-------------------

![Screenshot][localPDFScreenshot]

Use **PDFViewPager** class to load PDF from assets

1.- Copy your assets to cache directory (lib will do that for you in future versions)

    CopyAsset copyAsset = new CopyAssetThreadImpl(context, new Handler(), null); // You won't need 3rd parameter this time
    copyAsset.copy(asset, new File(getCacheDir(), "sample.pdf").getAbsolutePath());

2.- Create your **PDFViewPager** passing your PDF file, located in *assets* (see [sample][8])

    pdfViewPager = new PDFViewPager(this, "sample.pdf");

2b.- Or directly, declare it on XML

    <es.voghdev.pdfviewpager.library.PDFViewPager
        android:id="@+id/pdfViewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:assetFileName="sample.pdf"/>

3.- Release adapter in *onDestroy*

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ((PDFPagerAdapter)pdfViewPager.getAdapter()).close();
    }

TODOs
-----

- [ ] Load PDF documents from SD card
- [ ] Make PDF documents zoomable with pinch and double tap
- [ ] Unify all features in only one **PDFViewPager** class
- [X] Support API Levels under 21, by downloading PDF and invoking system native intent.
- [ ] UI tests

Developed By
------------

* Olmo Gallegos Hernández - [@voghDev][9] - [mobiledevstories.com][10]

<a href="http://twitter.com/voghDev">
  <img alt="Follow me on Twitter" src="http://imageshack.us/a/img812/3923/smallth.png" />
</a>
<a href="https://www.linkedin.com/profile/view?id=91543271">
  <img alt="Find me on Linkedin" src="http://imageshack.us/a/img41/7877/smallld.png" />
</a>

# License

    Copyright 2015 Olmo Gallegos Hernández

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

    fork the project into your GitHub account
    checkout your GitHub repo for the project
    implement your changes
    commit your changes, push them
    review your code and send me a pull request if you consider it

[remotePDFScreenshot]: ./screenshots/remote.gif
[localPDFScreenshot]: ./screenshots/local.gif
[8]: https://github.com/voghDev/PdfViewPager/tree/master/sample/src/main
[9]: http://twitter.com/voghDev
[10]: http://www.mobiledevstories.com
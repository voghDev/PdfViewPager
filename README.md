# PdfViewPager

[![Download](https://jitpack.io/v/voghDev/PdfViewPager.svg)](https://jitpack.io/#voghDev/PdfViewPager)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PdfViewPager-green.svg?style=true)](https://android-arsenal.com/details/1/3155)
[![Build Status](https://travis-ci.org/voghDev/PdfViewPager.svg?branch=master)](https://travis-ci.org/voghDev/PdfViewPager)

Android widget to display PDF documents in your Activities or Fragments.

Important note: **PDFViewPager** uses [PdfRenderer][6] class, which works **only on API 21 or higher**.
See [Official doc][6] for details.

If you are targeting pre-Lollipop devices, have a look at the [legacy sample][7]

## Installation

### Using [JitPack](https://jitpack.io/#voghDev/PdfViewPager)
1. Add this line in your project module `build.gradle` at the end of repositories:
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. Add this line in your app module `build.gradle` (`app/build.gradle`):
```gradle
implementation 'com.github.voghDev:PdfViewPager:1.1.2'
```

### Using JCenter (Deprecated)

> **Note**: JCenter has been deprecated and will be unavailable soon. Please use **JitPack** instead of.

Add this line in your app module `build.gradle` (`app/build.gradle`):
```gradle
implementation 'es.voghdev.pdfviewpager:library:1.1.2'
```

If you want to use the old `android.support` instead of `androidx`, add this dependency
```gradle
implementation 'es.voghdev.pdfviewpager:library:1.0.6'
```

## Usage

Use **PDFViewPager** class to load PDF files from assets or SD card

![Screenshot][localPDFScreenshot] ![Screenshot][zoomingScreenshot]

1. Copy your assets to cache directory if your PDF is located on assets directory
```java
CopyAsset copyAsset = new CopyAssetThreadImpl(context, new Handler());
copyAsset.copy(asset, new File(getCacheDir(), "sample.pdf").getAbsolutePath());
```

<details>
<summary>Kotlin</summary>
    
```kotlin
val copyAsset: CopyAsset = CopyAssetThreadImpl(context, Handler())
copyAsset.copy(asset, File(cacheDir, "sample.pdf").absolutePath)
```

</details>

2a. Create your **PDFViewPager** passing your PDF file, located in *assets* (see [sample][8])
```java
pdfViewPager = new PDFViewPager(this, "sample.pdf");
```

<details>
<summary>Kotlin</summary>
    
```kotlin
pdfViewPager = PDFViewPager(this, "sample.pdf")
```

</details>

2b. Or directly, declare it on your XML layout
```xml
<es.voghdev.pdfviewpager.library.PDFViewPager
    android:id="@+id/pdfViewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:assetFileName="sample.pdf" />
```

It will automatically have zooming and panning capability

3. Release adapter in *onDestroy*
```java
@Override
protected void onDestroy() {
    super.onDestroy();

    ((PDFPagerAdapter) pdfViewPager.getAdapter()).close();
}
```

<details>
<summary>Kotlin</summary>
    
```kotlin
override fun onDestroy() {
    super.onDestroy()
    (pdfViewPager.adapter as PDFPagerAdapter).close()
}
```

</details>


### PDF's on SD card

1. Create a **PDFViewPager** object, passing the file location in your SD card
```java
PDFViewPager pdfViewPager = new PDFViewPager(context, new File(getExternalFilesDir("pdf"), "adobe.pdf").getAbsolutePath());
```

<details>
<summary>Kotlin</summary>

```kotlin
val pdfViewPager = PDFViewPager(context, File(getExternalFilesDir("pdf"), "adobe.pdf").absolutePath)
```

</details>

2. Don't forget to release the adapter in *onDestroy*
```java
@Override
protected void onDestroy() {
    super.onDestroy();
    ((PDFPagerAdapter) pdfViewPager.getAdapter()).close();
}
```

<details>
<summary>Kotlin</summary>

```kotlin
override fun onDestroy() {
    super.onDestroy()
    (pdfViewPager!!.adapter as PDFPagerAdapter?)!!.close()
}
```

</details>

### Remote PDF's from a URL

![Screenshot][remotePDFScreenshot]

1. Add `INTERNET`, `READ_EXTERNAL_STORAGE` and `WRITE_EXTERNAL_STORAGE` permissions on your AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

2. Make your Activity or Fragment implement DownloadFile.Listener
```java
public class RemotePDFActivity extends AppCompatActivity implements DownloadFile.Listener {
```

<details>
<summary>Kotlin</summary>

```kotlin
class RemotePDFActivity: AppCompatActivity (), DownloadFile.Listener {
```

</details>

3. Create a **RemotePDFViewPager** object
```java
String url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";

RemotePDFViewPager remotePDFViewPager = new RemotePDFViewPager(context, url, this);
```

<details>
<summary>Kotlin</summary>

```kotlin
val url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf"

val remotePDFViewPager = RemotePDFViewPager(context, url, this)
```

</details>


4. Configure the corresponding callbacks and they will be called on each situation.
```java
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
```

<details>
<summary>Kotlin</summary>

```kotlin
override fun onSuccess(url: String?, destinationPath: String?) {
    // That's the positive case. PDF Download went fine

    adapter = PDFPagerAdapter(this, "AdobeXMLFormsSamples.pdf")
    remotePDFViewPager.setAdapter(adapter)
    setContentView(remotePDFViewPager)
}

override fun onFailure(e: Exception?) {
    // This will be called if download fails
}

override fun onProgressUpdate(progress: Int, total: Int) {
    // You will get download progress here
    // Always on UI Thread so feel free to update your views here
}
```

</details>

5. Don't forget to close adapter in *onDestroy* to release all resources
```java
@Override
protected void onDestroy() {
    super.onDestroy();

    adapter.close();
}
```

<details>
<summary>Kotlin</summary>

```kotlin
override fun onDestroy() {
    super.onDestroy()

    adapter.close()
}
```

</details>


### Usage in Kotlin

This library is fully usable in Kotlin programming language. Equivalent to all codes in the Kotlin language noted and you can also find example code [here][12].

## TODOs

- [X] Make initial Pdf scale setable by code *(requested by various users on issues)*
- [X] Load PDF documents from SD card
- [X] Make PDF documents zoomable with pinch and double tap (two approaches, [ImageViewZoom][5] and [photoview][11])
- [X] Unify all features in only one **PDFViewPager** and **PDFPagerAdapter** class
- [X] Support API Levels under 21, by downloading PDF and invoking system native intent.
- [X] UI tests
- [X] Add checkstyle, refactor & improve code quality

See [changelog][4] for details

## Contributing

### For noobs (like me some months ago)

1. Fork the project into your GitHub account
2. Now clone your GitHub repo for this project
3. Implement your changes
4. Commit your changes, push them into your repo
5. Review your code and send me a pull request if you consider it

### For not-so-noobs

Please make sure that your changes pass both checkstyle and UI tests before submitting them

```shell
./gradlew checkstyle

./gradlew test
```

And with your Android device connected
```shell
./gradlew connectedCheck
```

## Developed By

* Olmo Gallegos Hernández - [voghDev][9] - [mobiledevstories.com][10]

<a href="http://twitter.com/voghDev">
  <img alt="Follow me on Twitter" src="https://image.freepik.com/iconos-gratis/twitter-logo_318-40209.jpg" height="60" width="60" />
</a>
<a href="https://www.linkedin.com/profile/view?id=91543271">
  <img alt="Find me on Linkedin" src="https://image.freepik.com/iconos-gratis/boton-del-logotipo-linkedin_318-84979.png" height="60" width="60" />
</a>

## Support

This repository has been supported by JetBrains with free licenses for all JetBrains products

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/JetBrains_Logo_2016.svg/1200px-JetBrains_Logo_2016.svg.png" width="250" />

## License

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

[remotePDFScreenshot]: ./screenshots/remote.gif
[localPDFScreenshot]: ./screenshots/local.gif
[sdcardPDFScreenshot]: ./screenshots/sdcard.gif
[zoomingScreenshot]: ./screenshots/zooming.gif
[4]: https://github.com/voghDev/PdfViewPager/blob/master/CHANGELOG.md
[5]: https://github.com/sephiroth74/ImageViewZoom
[6]: http://developer.android.com/reference/android/graphics/pdf/PdfRenderer.html
[7]: https://github.com/voghDev/PdfViewPager/blob/27b61836168a09fec9287726246bd7b3da8aae74/sample/src/main/java/es/voghdev/pdfviewpager/LegacyPDFActivity.java
[8]: https://github.com/voghDev/PdfViewPager/tree/master/sample/src/main/java/es/voghdev/pdfviewpager
[9]: https://github.com/voghDev
[10]: http://www.mobiledevstories.com
[11]: https://github.com/chrisbanes/PhotoView
[12]: https://github.com/voghDev/HelloKotlin/blob/pdfviewpager/app/src/main/java/es/voghdev/hellokotlin/PdfViewPagerActivity.kt

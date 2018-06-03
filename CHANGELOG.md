# PdfViewPager changelog

1.0.4
-----

- Minor refactor to fix issue #86

1.0.3
-----

- Minor version changes from version `1.0.1` of the library
- Updated gradle version to `2.3.3`
- Updated build tools version to `25.0.3`
- Updated support library to `25.4.0`
- Thanks to [Deadpikle][7] for proposing these changes in [this Pull request](https://github.com/voghDev/PdfViewPager/pull/49)

1.0.2
-----

- Experimental branch that includes a vertical version of the widget
- Can be downloaded by using version 1.0.2 of the dependency, though it is not official yet

1.0.1
-----

- Upload new screenshots as [sample][7] project looks much better now
- Definitely eliminate LegacyPDFPagerAdapter and PdfViewPagerIVZoom classes, which were deprecated.
- Refactor PDFPagerAdapter to a Builder pattern, for constructing it in a more comfortable way.
- Add a "scale" parameter to PDFViewPagerZoom in XML files.
- Add an OnClickListener to each page of the PdfViewPager

1.0.0
-----

- PDF scale can now be set.
- Released all PhotoView resources to avoid possible leaks.

0.3.0
-----

- Drastically improved memory management (Thanks to [fkruege][6]'s implementations).
- Easier usage having PDFPagerAdapterZoom as the new PDFPagerAdapter class, because no one wants a PDF without zoom.
- Refactored to have a more flexible BitmapContainer classes.
- Added UI tests to memory management (initial idea from [fkruege][6]).
- Renamed the old PdfPagerAdapterIVZoom to LegacyPDFPagerAdapter. It is deprecated, but you can still use it.

0.2.1
-----

- New PdfViewPagerZoom widget, now using [PhotoView][4] library
    Note: Users porting from 0.2.0 won't notice anything. PdfViewPagerZoom will work as usual, but now using another library under the hood.
- The old [ImageViewZoom][5] implementation remains in PdfViewPagerIVZoom class, for anyone who still wants to use it.
- More UI Test coverage for the samples (lib users won't notice that, but I will :P)
- 0.2.1 uses a fixed version of ImageViewZoom, instead of latest version '+'

0.2.0
-----

- Added PdfViewPagerZoom using [ImageViewZoom][5] library
- Some UI Test coverage for the samples.

0.1.2
-----

- Initial PdfViewPager widget, without zooming and panning capabilities

[4]: https://github.com/chrisbanes/PhotoView
[5]: https://github.com/sephiroth74/ImageViewZoom
[6]: https://github.com/fkruege/
[7]: https://github.com/voghDev/PdfViewPager/tree/master/sample
[8]: https://github.com/Deadpikle
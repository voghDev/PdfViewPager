# PdfViewPager changelog

0.1.2
-----

- Initial PdfViewPager widget, without zooming and panning capabilities

0.2.0
-----

- Added PdfViewPagerZoom using [ImageViewZoom][5] library
- Some UI Test coverage for the samples.

0.2.1
-----

- New PdfViewPagerZoom widget, now using [PhotoView][4] library
    Note: Users porting from 0.2.0 won't notice anything. PdfViewPagerZoom will work as usual, but now using another library under the hood.
- The old [ImageViewZoom][5] implementation remains in PdfViewPagerIVZoom class, for anyone who still wants to use it.
- More UI Test coverage for the samples (lib users won't notice that, but I will :P)
- 0.2.1 uses a fixed version of ImageViewZoom, instead of latest version '+'

0.2.3
-----

- Drastically improved memory management (Thanks to [fkruege][6]'s implementations').
- Easier usage having PdfViewPagerZoom as the new PdfViewPager class, because no one wants a PDF without zoom.
- Refactored to have a more flexible BitmapContainer classes.
- Added UI tests to memory management (initial idea from [fkruege][6]).

[4]: https://github.com/chrisbanes/PhotoView
[5]: https://github.com/sephiroth74/ImageViewZoom
[6]: https://github.com/fkruege/PdfViewPager
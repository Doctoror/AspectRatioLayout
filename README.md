##Description

AspectRatioLayout is a FrameLayout that allows setting it's height relative to it's width and vice-versa.
You can make any View behave as AspectRatioLayout by implementing AspectRatioInterface and using AspectRatioDelegate.

##Example

```xml
    <!-- The height of this layout will always be as large as width -->
    <com.doctoror.aspectratiolayout.AspectRatioLayout
        android:layout_width="128dp"
        android:layout_height="wrap_content"
        demo:aspectType="vertical"
        demo:aspect="1"/>
        
    <!-- The width of this layout will always be as large as height -->
    <com.doctoror.aspectratiolayout.AspectRatioLayout
        android:layout_width="wrap_content"
        android:layout_height="128dp"
        demo:aspectType="horizontal"
        demo:aspect="1"/>
```

##License

```
Copyright 2015 Yaroslav Mytkalyk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```

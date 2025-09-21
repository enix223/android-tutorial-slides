
## RelativeLayout

- 相对布局，通过相对定位的方式让控件出现在布局的任何位置
- android:layout_alignParentLeft 父布局左对齐、android:layout_alignParentTop 父布局上对齐、
android:layout_alignParentRight 父布局右对齐、android:layout_alignParentBottom 父布局下对齐
、android:layout_centerInParent 父布局中心

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent">
    <Button
            android:id="@+id/button1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentLeft="true"
            android:layout_alignParentTop="true"
            android:text="Button 1" />
    <Button
            android:id="@+id/button2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_alignParentTop="true"
            android:text="Button 2" />
```

---

```xml
    <Button
            android:id="@+id/button3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="Button 3" />
    <Button
            android:id="@+id/button4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_alignParentLeft="true"
            android:text="Button 4" />
    <Button
            android:id="@+id/button5"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_alignParentRight="true"
            android:text="Button 5" />
</RelativeLayout>
```

--- 
# 以上五个按钮布局效果如下图：
<div class="flex flex-col items-center justify-center">
    <img src="/relative-layout.png" width="700"/>
</div>

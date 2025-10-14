# RelativeLayout
相对布局，通过相对定位的方式让控件出现在布局的任何位置

**相对于其他视图的位置 (Position Relative to Other Views)**

| 属性 (Property) | 应用对象 (Applied To) | 描述 (Description) | 
| ----- | ----- | ----- |
| `android:layout_toRightOf` | 子视图 (Child View) | 将当前视图的左边缘放置在指定视图（通过其 ID 引用）的右边缘。 | 
| `android:layout_below` | 子视图 (Child View) | 将当前视图的上边缘放置在指定视图（通过其 ID 引用）的下边缘。 | 
| `android:layout_alignTop` | 子视图 (Child View) | 使当前视图的上边缘与指定视图的上边缘对齐。 | 
| `android:layout_alignBottom` | 子视图 (Child View) | 使当前视图的下边缘与指定视图的下边缘对齐。 | 

**注意：** 对于任何引用其他视图的属性（例如 `layout_toRightOf`），您必须使用该视图的唯一 ID，即通过 `android:id="@+id/my_view_id"` 指定的 ID

---

# RelativeLayout

**相对于父布局的位置 (Position Relative to Parent)**
| 属性 (Property) | 应用对象 (Applied To) | 描述 (Description) | 
| ----- | ----- | ----- |
| `android:layout_alignParentTop` | 子视图 (Child View) | 如果为 `true`，将视图的上边缘与父布局的上边缘对齐。 | 
| `android:layout_alignParentBottom` | 子视图 (Child View) | 如果为 `true`，将视图的下边缘与父布局的下边缘对齐。 | 
| `android:layout_alignParentRight` | 子视图 (Child View) | 如果为 `true`，将视图的右边缘与父布局的右边缘对齐。 | 
| `android:layout_alignParentLeft` | 子视图 (Child View) | 如果为 `true`，将视图的左边缘与父布局的左边缘对齐。 | 

---

# RelativeLayout

**居中 (Centering)**

| 属性 (Property) | 应用对象 (Applied To) | 描述 (Description) | 
| ----- | ----- | ----- |
| `android:layout_centerInParent` | 子视图 (Child View) | 如果为 `true`，在父布局中水平和垂直居中显示视图。 | 
| `android:layout_centerHorizontal` | 子视图 (Child View) | 如果为 `true`，在父布局中水平居中显示视图。 | 
| `android:layout_centerVertical` | 子视图 (Child View) | 如果为 `true`，在父布局中垂直居中显示视图。 | 

---

# RelativeLayout使用示例

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

# RelativeLayout使用示例（续上）

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

# RelativeLayout效果图

<div class="flex flex-col items-center justify-center">
    <img src="/relative-layout.png" width="600"/>
</div>

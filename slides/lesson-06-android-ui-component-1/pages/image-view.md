
## ImageView

- ImageView是Android系统中常用的组件之一，主要用于显示图片。
ImageView继承于View，因此具有的View的所有功能。


<div class="flex flex-col items-center justify-center">
    <img src="/image-view-1.png"  width="750"/>
</div>

---

```xml 
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:gravity="center">

  <ImageView
    android:id="@+id/iv_icon1"
    android:layout_width="500px"
    android:layout_height="300px"
    android:background="@color/black"
    android:src="@drawable/ic_launcher_background" />

</LinearLayout>
```

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        ImageView iv = findViewById(R.id.iv_icon1);  
        iv.setImageResource(R.drawable.ic_launcher_background);
        iv.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.black));
  }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/image-view-2.png"  width="200"/>
</div>


<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：通过点击事件来更改ImageView的图片和颜色 </div>
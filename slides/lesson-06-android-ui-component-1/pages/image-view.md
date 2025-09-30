# ImageView

- ImageView是Android系统中常用的组件之一，主要用于显示图片。ImageView继承于View，因此具有的View的所有功能。

| 属性 | 作用 | 说明 |
|-|-|-|
| android:src | 图片源 | 来自于res/drawable文件夹或res/mimap文件夹的图片或图形定义 |
| android:scaleType | 图片缩放方式 | 具体取值看下表;  |

---

# ImageView

scaleType

| 值 | 说明 |
|-|-|
| center | 图片在ImageView中居中，图片不缩放，超出部分不显示 |
| fitCenter | 图片在ImageView中居中，根据ImageView的大小保持比例缩放完全显示在ImageView中，ImageView可能有留白的地方 |
| centerCrop | 图片在ImageView中居中，根据ImageView的大小保持比例缩放，ImageView被完全填满，但是图片可能会被截取部分 |
| centerInside | 图片在ImageView中居中，根据ImageView的大小保持比例缩放，如果图片比ImageView小，则保持原始尺寸 |
| fitStart | 根据ImageView的大小保持比例缩放，图片在左上角对齐显示 |
| fitEnd | 根据ImageView的大小保持比例缩放，图片在右下角对齐显示 |
| fitXY | 根据ImageView的大小不保持比例缩放，图片缩放至跟ImageView的尺寸一样，图片可能会变形 |
| matrix | 一般配合程序使用，使用较少 |

---

# ImageView

```xml 
<ImageView
android:id="@+id/iv_icon1"
android:layout_width="500px"
android:layout_height="300px"
android:background="@color/black"
android:src="@drawable/ic_launcher_background" />
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

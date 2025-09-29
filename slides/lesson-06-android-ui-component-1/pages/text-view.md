
# TextView

- TextView是一个用于显示文本的控件。它可以显示单行或多行文本，并且支持丰富的文本样式和格式。

| 属性 | 作用 | 说明 |
|-|-|-|
| android:text | 显示的文本 | 用于设置要显示的文本 |
| android:textSize | 字体大小 | 设置文本的字体大小，例如20sp |
| android:textColor | 字体颜色 | 设置文本的颜色，例如#FF00FF00 |
| android:textStyle | 字体风格 | 例如设置斜体italic, 粗体bold |
| android:fontFamily | 文本字体 | 例如sans-serif-medium |

```xml 
<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello, World!"
    android:textSize="20sp"
    android:textColor="#FF0000" />
```

---

# TextView

- 在Java代码中，可以通过以下方式访问和修改TextView：

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        TextView textView = findViewById(R.id.textView);
        textView.setText("New Text");
        textView.setTextSize(24);
        textView.setTextColor(Color.BLUE);
  }
}
```

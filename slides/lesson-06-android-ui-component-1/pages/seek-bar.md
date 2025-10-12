# SeekBar

SeekBar意思为拖动条，是ProgressBar的一个子类。
在我们安卓的开发中也是应用非常的广泛。如音乐播放、音量条、播放进度条，等等。Android系统只提供了水平的，默认的样式，我们也可以根据自己需求自定义样式。

SeekBar的事件，SeekBar.OnSeekBarChangeListener 我们只需重写三个对应的方法：

* onProgressChanged：进度发生改变时会触发
* onStartTrackingTouch：按住SeekBar时会触发
* onStopTrackingTouch：放开SeekBar时触发

| 属性 | 说明 |
| :--- | :--- |
| **android:max** | 进度条的最大值 |
| **android:min** | 进度条的最小值 |
| **android:progress** | 滑块初始位置或当前位置 |
| **android:progressDrawable** | 设置进度条的图片 |
| **android:thumb** | 设置滑块的图片 |
| **android:thumbTint** | 设置滑块颜色 |

---

# SeekBar的使用

```xml 
<SeekBar
    android:id="@+id/sb_normal"
    android:layout_marginLeft="15dp"
    android:layout_marginRight="15dp"
    android:splitTrack="false"
    android:max="100"
    android:progress="60"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:thumb = "@mipmap/ic_launcher"/>
```

---

# SeekBar与Activity的交互

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        SeekBar sb_normal = findViewById(R.id.sb_normal);
        TextView txt_cur = findViewById(R.id.txt_cur);

        sb_normal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_cur.setText("当前进度值:" + progress + "  / 100 ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "触碰SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "放开SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

---

# SeekBar练习

<div class="flex flex-col items-center justify-center">
    <img src="/seek-bar-2.gif" width="200"/>
</div>

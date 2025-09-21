
## SeekBar

- SeekBar意思为拖动条，是ProgressBar的一个子类。
  在我们安卓的开发中也是应用非常的广泛。如音乐播放、音量条、播放进度条，等等。Android系统只提供了水平的，默认的样式，我们也可以根据自己需求自定义样式。

- SeekBar的事件，SeekBar.OnSeekBarChangeListener 我们只需重写三个对应的方法：
  onProgressChanged：进度发生改变时会触发
  onStartTrackingTouch：按住SeekBar时会触发
  onStopTrackingTouch：放开SeekBar时触发


<div class="flex flex-col items-center justify-center">
    <img src="/seek-bar-1.png" width="780"/>
</div>

---


```xml 
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:layout_marginTop="20dp"
  android:orientation="vertical"
  android:gravity="top">


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

  <TextView
    android:id="@+id/txt_cur"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="Hello, World!"
    android:textSize="20sp"
    android:textColor= "@color/black"/>

</LinearLayout>
```

---

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

<div class="flex flex-col items-center justify-center">
    <img src="/seek-bar-2.gif" width="200"/>
</div>

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：使用SeekBar来设置图片的透明度（ImageView 的setAlpha(int i) 方法可以设置透明度）</div>
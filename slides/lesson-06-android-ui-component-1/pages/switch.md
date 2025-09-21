
## Switch 

- 用于布尔开关的控件，带开关状态和文本,允许我们在两个状态之间切换，有点类似于滑动解锁


<div class="flex flex-col items-center justify-center">
    <img src="/switch-1.png" width="780"/>
</div>

---

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="8dp" 
    android:orientation="horizontal" >

    <Switch 
        android:id="@+id/power"
        android:text="电视机状态:"
        android:textOn = "开机"
        android:textOff = "关机"
        android:checked="true" 
        android:layout_width="wrap_content" 
        android:layout_height="wrap_content" />

</LinearLayout>
```

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        Switch power = findViewById(R.id.power);
        power.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                String state = compoundButton.isChecked() ? "开机" :"关机";
                Toast.makeText(MainActivity.this,"电视机当前状态:"+state,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```


---

<div class="flex flex-col items-center justify-center">
    <img src="/switch-2.png" width="300"/>
</div>

---

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：制作一个可以输入账号密码登陆的页面，并实现功能（账号不为admin，密码不为123456时提示账号或密码出错）,登录成功时，判断开关状态：
若开关为 开，提示“登录成功，已记住密码”；
若开关为 关，提示“登录成功，不记住密码”。 </div>
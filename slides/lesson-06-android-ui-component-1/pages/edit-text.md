
## EditText

-  EditText 在 TextView 的基础之上多了一个输入的功能；从代码上 EditText 是继承自 TextView 的子类，所以我们可以大胆的理解为， EditText 是一种带有输入功能的高级 TextView


<div class="flex flex-col items-center justify-center">
    <img src="/edit-text-1.png" width="780"/>
</div>

---

```xml 
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="20dp">
    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:text="确定" />
    <EditText
        android:id="@+id/input_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginRight="10dp"
        android:layout_toLeftOf="@id/button"
        android:hint="请输入密码"
        android:imeOptions="actionDone"
        android:inputType="textPassword"
        android:maxLines="5"
        android:textSize="20sp" />
</RelativeLayout>
```

---

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        EditText editText = findViewById(R.id.input_password);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/edit-text-2.png"/>
</div>

---

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：制作一个可以输入账号密码登陆的页面，并实现功能（账号不为admin，密码不为123456时提示账号或密码出错） </div>
# EditText
EditText 在 TextView 的基础之上多了一个输入的功能；从代码上 EditText 是继承自 TextView 的子类，所以我们可以大胆的理解为， EditText 是一种带有输入功能的高级 TextView

<p class="my-2"></p>

| 属性 | 说明 |
| :--- | :--- |
| **android:hint** | 控件中内容为空时显示的提示文本信息 |
| **android:textColorHint** | 控件中内容为空时显示的提示文本信息的颜色 |
| **android:password** | 输入文本框中的内容显示为 "\*" |
| **android:phoneNumber** | 设置输入文本框中的内容只能是数字 |
| **android:minLines** | 设置文本的最小行数 |
| **android:scrollHorizontally** | 设置文本信息超出 |
| **android:editable** | 设置是否可编辑 |

---

# EditText

```xml 
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
```

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

# EditText
显示效果展示

<div class="flex flex-col items-center justify-center">
    <img src="/edit-text-2.png" class="w-[20%]" />
</div>

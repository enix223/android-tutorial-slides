

# 示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="52dp"
        android:text="增加数据"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
    <Button
        android:id="@+id/button4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="修改数据"
        app:layout_constraintEnd_toEndOf="@+id/button2"
        app:layout_constraintStart_toStartOf="@+id/button2"
        app:layout_constraintTop_toBottomOf="@+id/button2" />
```

---

```xml
    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="删除数据"
        app:layout_constraintEnd_toEndOf="@+id/button4"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="@+id/button4"
        app:layout_constraintTop_toBottomOf="@+id/button4" />
    <Button
        android:id="@+id/button5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="查询数据"
        app:layout_constraintEnd_toEndOf="@+id/button3"
        app:layout_constraintStart_toStartOf="@+id/button3"
        app:layout_constraintTop_toBottomOf="@+id/button3" />
    <Button
        android:id="@+id/button6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text="清除数据"
        app:layout_constraintEnd_toEndOf="@+id/button5"
        app:layout_constraintStart_toStartOf="@+id/button5"
        app:layout_constraintTop_toBottomOf="@+id/button5" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

----


```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button2); //add
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences对象
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                //获取Editor对象的引用
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //将获取过来的值放入文件
                editor.putString("name", "lucas");
                editor.putInt("age", 30);
                editor.putBoolean("islogin", true);
                // 提交数据
                editor.commit();
            }
        });
        Button button2 = findViewById(R.id.button3); //删
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
```

---

```java
                //获取SharedPreferences对象
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                //获取Editor对象的引用
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //将获取过来的值放入文件
                editor.remove("name");
                // 提交数据
                editor.commit();
            }
        });
        Button button3 = findViewById(R.id.button4); //改
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences对象
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                //获取Editor对象的引用
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //将获取过来的值放入文件
                editor.putString("name", "lucas1");
                editor.putInt("age", 31);
                editor.putBoolean("islogin", false);
                // 提交数据
                editor.commit();
            }
        });
```

---

```java
        Button button4 = findViewById(R.id.button5); //查
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "");
                int age = sharedPreferences.getInt("age", 0);
                boolean islogin = sharedPreferences.getBoolean("islogin", true);
                Log.i("lucashu", "name:" + name + " age:" + age + " islogin:" + islogin);
            }
        });
        Button button5 = findViewById(R.id.button6); //清除数据
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences对象
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                //获取Editor对象的引用
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                // 提交数据
                editor.commit();
            }
        });
    }
}
```
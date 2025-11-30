

# data/data/(包名) 目录文件读写

* 将数据以普通文件的形式保存在 /data/data/包名中，该方法不需要申请权限。

* 存放在数据区(/data/data/包名)的文件可以使用openFileOutput和openFileInput进行操作。

* 也可以直接指定文件/data/data/包名路径读写。

* 该目录存放文件类型规则如下：

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">路径</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">功能</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">data/data/包名/shared_prefs</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">存放SharedPreferences数据</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">data/data/包名/databases</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">存放数据库数据</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">data/data/包名/files</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">存放普通数据</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">data/data/包名/cache</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">存放缓存文件</div>
  </div>
</div>

---

# 写数据

* 方法1：openFileOutput

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileOutputStream fileOutputStream = null;
        try {
            String text = "hello world";
            fileOutputStream = openFileOutput("test.txt", MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

---

# openFileOutput方法参数详解


```java
@Override
public FileOutputStream openFileOutput(String name, int mode)
    throws FileNotFoundException {
        
}
```

* name 存储文件名字
* mode 存储方式 其值的含义如下


<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">参数值</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">参数含义</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_PRIVATE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">指定该文件数据只能被本应用程序读、写</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_WORLD_READABLE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">指定该文件数据能被其他应用程序读，但不能写</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_WORLD_WRITEABLE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">指定该文件数据能被其他应用程序读</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_APPEND</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">该模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件</div>
  </div>
</div>

---

* 方法2：传统方法读写

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String text = "hello world";
        File file1=new File("/data/data/com.lucashu.file/","test.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-file-6.png"/>
</div>

---

# 读数据

* 方法1：openFileInput

```java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		FileInputStream fileInputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = openFileInput("test.txt");
            reader = new InputStreamReader(fileInputStream);// 字符流
            bufferedReader = new BufferedReader(reader); //缓冲流
            StringBuilder result = new StringBuilder();
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                result.append(temp);
            }
            Log.i("MainActivity", "result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
```

---

```java
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```


---

* 方法2：openFileInput

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {

            File file=new File("/data/data/com.lucashu.file/", "test.txt");
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            StringBuilder result = new StringBuilder();
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                result.append(temp);
            }
            Log.i("MainActivity", "result:" + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
```

---

```java
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
```
---

我们也可以用一下方法来获取改文件的路径

```java
getFilesDir();// data/data/包名/files 目录
getFilesDir().getParent(),;// data/data/包名/ 目录
```

<br />

<div class="flex flex-col items-center justify-center">
    <img src="/android-file-7.png"/>
</div>
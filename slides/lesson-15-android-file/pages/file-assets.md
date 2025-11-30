
# 读取assets目录文件

* Android工程默认是没有assets文件夹的，在读文件前我们先把assets 文件夹以及文件建好

1. 点中工程 右键New -> Folder -> Assets Folder

2. 点中刚刚新建的Assets Folde目录 右键New -> File -> 弹窗中输入test.txt

3. 点开刚刚新建的test.txt文件 ，往里面输入hello world

<div class="flex flex-col items-center justify-center">
    <img src="/android-file-4.gif" width="600"/>
</div>

---

* 读取assets文件中的数据方法

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
            //得到资源中的asset数据流
            inputStream = getResources().getAssets().open("test.txt");
            reader = new InputStreamReader(inputStream);// 字符流
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

* 从打印的结果中我们可以看出数据读取成功

<div class="flex flex-col items-center justify-center">
    <img src="/android-file-5.png"/>
</div>

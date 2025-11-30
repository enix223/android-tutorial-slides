# sdcard文件读写

* sdcard读写的数据将数据保存在 /mnt/sdcard目录,该目录读写需要先申请动态权限,首先需要在manifest.xml中添加权限。（Android 10+（API 29+）不允许直接访问外部存储根目录）

```xml

<!--文件读写权限-->
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

* 完整项目 

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="20dp">

    <Button
        android:id="@+id/btn_write"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="写入 SD 卡"
        android:onClick="onWriteClick"
        android:padding="12dp" />

```

---

```xml

    <Button
        android:id="@+id/btn_read"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="读取 SD 卡"
        android:onClick="onReadClick"
        android:layout_marginTop="20dp"
        android:padding="12dp" />

</LinearLayout>
```

---

```java

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }

    public void onWriteClick(View view) {
        writeSdcard();
    }

    public void onReadClick(View view) {
        readSdcard();
    }


```

---

```java
    private void writeSdcard() {
        String text = "hello world";

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File storage = Environment.getExternalStorageDirectory();
            File tempFile = new File(storage.getPath());

            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }

            File file1 = new File(tempFile, "test.txt");
            if (!file1.exists()) {
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file1);
                fileOutputStream.write(text.getBytes());
                Toast.makeText(this, "文件写入成功！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

```

---

```java
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

    private void readSdcard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            InputStream inputStream = null;
            Reader reader = null;
            BufferedReader bufferedReader = null;

            try {
                File storage = Environment.getExternalStorageDirectory();
                File tempFile = new File(storage.getPath());

                File file = new File(tempFile, "test.txt");
                inputStream = new FileInputStream(file);
                reader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(reader);

```

---

```java
                StringBuilder result = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    result.append(temp);
                }

                Log.i("MainActivity", "result: " + result);
                Toast.makeText(this, "读取到内容：" + result, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "读取失败！", Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    if (reader != null) reader.close();
                    if (inputStream != null) inputStream.close();
                    if (bufferedReader != null) bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

```

---

```java
    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "已授权成功！", Toast.LENGTH_SHORT).show();
        }
    }

```

---

```java
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "授权被拒绝！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
```

---


<div class="flex flex-col items-center justify-center">
    <img src="/android-file-8.png"/>
</div>

<br />

<div class="flex flex-col items-center justify-center">
    <img src="/android-file-9.png"/>
</div>
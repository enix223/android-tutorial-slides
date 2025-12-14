# 什么是 HttpURLConnection？
**HttpURLConnection** 是 Java 标准库内置的 HTTP 客户端类，用于在 Java 或 Android 中进行 HTTP 网络请求。

它轻量、无第三方依赖，适合入门者学习 HTTP 请求的基本流程。


## HttpURLConnection 的特点
- **JDK 原生库**，不需要额外依赖。
- 支持 **GET、POST 等常见方法**。
- 可设置 **请求头、超时时间、缓存控制** 等。
- 可获取 **响应码、响应头、响应内容**。
- Android 系统长期支持，适合学习网络基础。

---

#  使用 HttpURLConnection 的步骤
HttpURLConnection 使用流程非常固定，可以总结为：

1. **创建 URL 对象**
2. **打开连接并转换为 HttpURLConnection**
3. **设置请求方法（GET/POST）**
4. **配置超时、请求头等**
5. **如果是 POST：写入请求体**
6. **连接并获取响应码**
7. **读取响应内容**
8. **关闭连接和流**

* 需要先在AndroidManifest.xml 添加网络权限

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

# GET 请求示例
```java
URL url = new URL("https://example.com/api/data");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
conn.setConnectTimeout(5000);
conn.setReadTimeout(5000);

int code = conn.getResponseCode(); // 获取响应码

if (code == HttpURLConnection.HTTP_OK) {
    InputStream is = conn.getInputStream();
    String result = inputStreamToString(is);
    System.out.println(result);
}

conn.disconnect();
```

---

# POST 请求示例
```java
URL url = new URL("https://example.com/api/login");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();

conn.setRequestMethod("POST");
conn.setDoOutput(true);
conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

String body = "{"username":"Tom","password":"123456"}";
OutputStream os = conn.getOutputStream();
os.write(body.getBytes());
os.flush();

int code = conn.getResponseCode();

InputStream is = (code == HttpURLConnection.HTTP_OK)
        ? conn.getInputStream()
        : conn.getErrorStream();

String response = inputStreamToString(is);
System.out.println(response);

conn.disconnect();
```

---

# 常用配置
```java
conn.setConnectTimeout(5000);  // 连接超时
conn.setReadTimeout(5000);     // 读取超时
conn.setUseCaches(false);      // 禁用缓存
conn.setRequestProperty("User-Agent", "MyApp"); // 自定义请求头
```

---

# 注意事项
- HttpURLConnection 适合简单场景；复杂场景可使用 OkHttp、Retrofit 等。
- 不要忘记关闭 stream 和 disconnect()。
- 在 Android 上不要在主线程使用，必须放在子线程。



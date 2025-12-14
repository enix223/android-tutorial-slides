package com.enixyu.httpurlconnectiondemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

  private TextView tvResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btnGet = findViewById(R.id.btnLoadGet);
    Button btnPost = findViewById(R.id.btnLoadPost);
    tvResult = findViewById(R.id.tvResult);

    btnGet.setOnClickListener(v -> loadWeatherByGet());
    btnPost.setOnClickListener(v -> loadWeatherByPost());
  }

  /**
   * GET 请求：真实获取天气
   */
  private void loadWeatherByGet() {
    new Thread(() -> {
      try {
        String urlStr =
            "https://api.open-meteo.com/v1/forecast" +
                "?latitude=39.9042" +
                "&longitude=116.4074" +
                "&current_weather=true";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int code = conn.getResponseCode();

        if (code == HttpURLConnection.HTTP_OK) {
          String json = readStream(conn.getInputStream());

          JSONObject root = new JSONObject(json);
          JSONObject current = root.getJSONObject("current_weather");

          double temp = current.getDouble("temperature");
          double wind = current.getDouble("windspeed");

          String result =
              "【GET 天气】\n" +
                  "温度：" + temp + " °C\n" +
                  "风速：" + wind + " km/h";

          runOnUiThread(() -> tvResult.setText(result));
        }

        conn.disconnect();
      } catch (Exception e) {
        showError(e);
      }
    }).start();
  }

  /**
   * POST 请求：演示 POST 流程（真实接口）
   * httpbin.org 会把你 POST 的数据原样返回
   */
  private void loadWeatherByPost() {
    new Thread(() -> {
      try {
        URL url = new URL("https://httpbin.org/post");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        // 模拟提交城市信息
        String body = "{\"city\":\"Beijing\"}";

        OutputStream os = conn.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();

        int code = conn.getResponseCode();

        if (code == HttpURLConnection.HTTP_OK) {
          String response = readStream(conn.getInputStream());

          String result =
              "【POST 演示】\n" +
                  "服务器返回：\n" +
                  response;

          runOnUiThread(() -> tvResult.setText(result));
        }

        conn.disconnect();
      } catch (Exception e) {
        showError(e);
      }
    }).start();
  }

  private String readStream(InputStream is) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    reader.close();
    return sb.toString();
  }

  private void showError(Exception e) {
    runOnUiThread(() ->
        tvResult.setText("错误：" + e.getMessage()));
  }
}

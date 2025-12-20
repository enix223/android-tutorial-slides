package com.enixyu.cookbook.port;

import android.util.Log;
import com.alibaba.fastjson2.JSON;
import com.enixyu.cookbook.BuildConfig;
import com.enixyu.cookbook.model.Weather;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemReader;
import org.json.JSONObject;

public class QWeather {

  private final static String TAG = "QWeather";
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  static {
    // Check if the current "BC" provider is the one we want
    Provider existingProvider = Security.getProvider("BC");

    if (existingProvider != null && !existingProvider.getClass()
        .equals(BouncyCastleProvider.class)) {
      // If it's Android's internal version, remove it and insert the full one
      Security.removeProvider("BC");
      Security.insertProviderAt(new BouncyCastleProvider(), 1);
    } else if (existingProvider == null) {
      // If not present at all, just add it
      Security.addProvider(new BouncyCastleProvider());
    }
  }

  public Future<Weather> getRealtimeWeather() {
    return executor.submit(() -> {
      HttpURLConnection urlConnection = null;
      try {
        URL url = new URL(BuildConfig.HEWEATHER_URL + "/v7/weather/now?location=101010100");
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000);
        String token = buildToken();
        urlConnection.setRequestProperty("Authorization", "Bearer " + token);
        int code = urlConnection.getResponseCode();
        String body = readStream(urlConnection.getInputStream());
        if (code == HttpURLConnection.HTTP_OK) {
          JSONObject root = new JSONObject(body);
          JSONObject now = root.getJSONObject("now");
          String temperature = now.getString("temp");
          String humidity = now.getString("humidity");
          String windSpeed = now.getString("windSpeed");
          return new Weather(temperature, humidity, windSpeed);
        } else {
          Log.e(TAG, "获取天气失败:" + code + "body = " + body);
        }
      } catch (Exception e) {
        Log.e(TAG, "获取天气失败", e);
      } finally {
        if (urlConnection != null) {
          urlConnection.disconnect();
        }
      }
      return null;
    });
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

  // 构造身份
  private String buildToken() {
    var now = ZonedDateTime.now(ZoneOffset.UTC);

    // Sign JWT with Ed25519 private key
    try {
      // load private key
      var privateKeyString = String.format(
          "-----BEGIN PRIVATE KEY-----\n%s\n-----END PRIVATE KEY-----",
          BuildConfig.HEWEATHER_PRIVATE_KEY);
      PrivateKey privateKey;
      try (var pemReader = new PemReader(new StringReader(privateKeyString))) {
        KeyFactory keyFactory = KeyFactory.getInstance("EdDSA", "BC");
        var keySpec = new PKCS8EncodedKeySpec(pemReader.readPemObject().getContent());
        privateKey = keyFactory.generatePrivate(keySpec);
      }

      var headerJson = JSON.toJSONString(
          Map.of("alg", "EdDSA", "kid", BuildConfig.HEWEATHER_KEY_ID));

      // Payload
      long iat = now.toEpochSecond();
      long exp = now.plusHours(1).toEpochSecond();
      var payloadJson = JSON.toJSONString(Map.of(
          "sub", BuildConfig.HEWEATHER_APP_ID,
          "iat", iat,
          "exp", exp
      ));

      // Base64url header+payload
      var headerEncoded = Base64.getUrlEncoder()
          .encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
      var payloadEncoded = Base64.getUrlEncoder()
          .encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));
      var data = headerEncoded + "." + payloadEncoded;
      var dataBytes = data.getBytes(StandardCharsets.UTF_8);

      // Sign
      var s = Signature.getInstance("EdDSA", "BC");
      s.initSign(privateKey);
      s.update(dataBytes);
      byte[] signature = s.sign();

      var sign = Base64.getUrlEncoder().encodeToString(signature);
      return data + "." + sign;
    } catch (Exception e) {
      Log.e(TAG, "构造token失败", e);
      throw new RuntimeException("failed to build jwt token", e);
    }
  }
}

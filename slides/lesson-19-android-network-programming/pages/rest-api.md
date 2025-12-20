# 如何调用外部HTTP API接口
一些公共服务的厂商，会提供一个HTTP API接口，可以让开发者获取其数据。

* <span class="text-blue-700">天气数据接口</span>，可以查询实时的天气信息、获取天气预报
* <span class="text-blue-700">快递查询接口</span>，可以根据运单号查询快递的最新派送路径信息
* <span class="text-blue-700">汇率转换API</span>，支持多种货币的实时汇率转换
* <span class="text-blue-700">地图逆地址转换</span>，可以通过手机获取的定位坐标，转换为地址门牌信息
* <span class="text-blue-700">语音识别API</span>，例如百度的语音识别API，开发者可以上传一段音频后，转换为对应的文字返回

---

# 和风天气API
以下以和风天气API的调用为例，说明如何查看实时的天气信息

### 和风API文档

[https://dev.qweather.com/docs/api/weather/weather-now/](https://dev.qweather.com/docs/api/weather/weather-now/)

<br />

### API文档中一般包含如下信息

* <span class="text-blue-700">请求路径</span>，例如`GET /v7/weather/now`
* <span class="text-blue-700">入参</span>，API请求接受的参数，录入我们要查询实时天气，那么入参就需要包括位置信息
* <span class="text-blue-700">返回数据格式</span>，解释返回的数据的格式，例如返回的是JSON，则需要说明每个字段的作用。
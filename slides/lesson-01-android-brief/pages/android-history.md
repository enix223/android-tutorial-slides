# Android的诞生

一个移动互联网时代的开端

<img v-click src="/android-brief/android-logo.svg" class="my-5" />

<ul>
  <li v-click>
      <span>🎊 2003年10月，由安卓之父安迪·鲁宾 (Andy Rubin)创建</span>
      <img v-click src="/android-brief/android-founder.jpg" class="h-25" />
  </li>
  <li v-click>🛒 2005年，Google收购了Android公司</li>
  <li v-click>👐 2007年，Google把Android开源，AOSP (Android Open Source Projec)，手机厂商仍然需要付费获得非开源版本。</li>
</ul>

---

<style>
.android-table {
  font-size: 0.7em;
}
.android-table table {
  width: 100%;
}
.android-table th {
  background: #5ce198;
  color: #000;
  padding: 0.3rem;
}
.android-table td {
  padding: 0.3rem;
  border-bottom: 1px solid #ddd;
}
</style>

# Android历史版本

<div class="grid grid-cols-2 gap-4 android-table" v-click>

| 名称 | 发行日期 | API等级 |
|------|----------|---------|
| Android 1.0 | 2008年9月23日 | 1 |
| Android 1.1 | 2009年2月9日 | 2 |
| Android Cupcake | 2009年4月27日 | 3 |
| Android Donut | 2009年9月15日 | 4 |
| Android Eclair | 2009年10月26日 | 5 – 7 |
| Android Froyo | 2010年5月20日 | 8 |
| Android Gingerbread | 2010年12月6日 | 9 – 10 |
| Android Honeycomb | 2011年2月22日 | 11 – 13 |
| Android Ice Cream Sandwich | 2011年10月18日 | 14 – 15 |
| Android Jelly Bean | 2012年7月9日 | 16 – 18 |

| 名称 | 发行日期 | API等级 |
|------|----------|---------|
| Android KitKat | 2013年10月31日 | 19 – 20 |
| Android Lollipop | 2014年11月12日 | 21 – 22 |
| Android Marshmallow | 2015年10月5日 | 23 |
| Android Nougat | 2016年8月22日 | 24 – 25 |
| Android Oreo | 2017年8月21日 | 26 – 27 |
| Android Pie | 2018年8月6日 | 28 |
| Android 10 | 2019年9月3日 | 29 |
| Android 11 | 2020年9月8日 | 30 |
| Android 12 | 2021年10月4日 | 31–32 |
| Android 13 | 2022年8月15日 | 33 |
| Android 14 | 2023年10月4日 | 34 |
| Android 15 | 2024年10月15日 | 35 |
| Android 16 | 2025年6月10日 | 36 |

</div>

---

# Android系统架构总览

<div class="flex flex-row gap-3">

<div class="flex flex-1">
  <img src="/android-brief/android-system-architecture.svg" />
</div>
<div class="flex flex-1">
<ul>
  <li v-click>Application层: app应用，例如微信、QQ、浏览器等等</li>
  <li v-click>Application Framework: 连接上层应用与底层操作系统的桥梁，为上层提供API接口与工具。包括四大组件：Activities、Services、Broadcast Receivers、Content Provider</li>
  <li v-click>核心库, ART: 安卓app的运行环境和核心库。</li>
  <li v-click>Linux内核: 底层操作系统核心，实现与硬件驱动交互、进程管理、内存管理、存储管理等</li>
</ul>
</div>

</div>

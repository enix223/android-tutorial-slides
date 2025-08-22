---
theme: default
colorSchema: light
---

# 关于这门课程

纸上得来终觉浅，绝知此事要躬行

<ul class="mb-5">
  <li v-click>⚒️ 工欲善其事，必先利其器。准备好一台好的电脑，一部安卓手机。</li>
  <li v-click>🤲 安卓开发是一门实战课，必须要亲身动手参与实战。</li>
  <li v-click>🧠 熟记常用的开发的模式，做项目就事半功倍。</li>
  <li v-click>🤖 借助AI工具，提高生产力；但也不要完全依赖它，要知其然知其所以然。</li>
  <li v-click>💻 站在巨人的肩膀上，少刷短视频，多去github看看流行项目的源码。</li>
</ul>


<v-click>📖 推荐阅读</v-click>

<div class="flex flex-row gap-2">
  <div class="flex flex-col justify-center flex-1" v-click>
    <img src="/android-brief/android-book-java-core.jpg" class="w-25 h-30" />
    <div class="text-sm">Java核心技术（第11版）</div>
  </div>
  <div class="flex flex-col justify-center flex-1" v-click>
    <img src="/android-brief/android-book-first-line-of-code.jpg" class="w-25 h-30" />
    <div class="text-sm">第一行代码（第三版）</div>
  </div>
  <div class="flex flex-col justify-center flex-1" v-click>
    <img src="/android-brief/android-book-internal-deep.jpg" class="w-25 h-30" />
    <div class="text-sm">深入理解Android内核设计思想（第2版 套装上下册）</div>
  </div>
  <div class="flex flex-col justify-center flex-1" v-click>
    <img src="/android-brief/android-book-jetpack-compose.jpg" class="w-25 h-30" />
    <div class="text-sm">Jetpack compose 从入门到实践</div>
  </div>
  <div class="flex flex-col justify-center flex-1" v-click>
    <img src="/android-brief/android-book-kotlin-in-action.jpg" class="w-25 h-30" />
    <div class="text-sm">Kotlin实战</div>
  </div>
</div>
---

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

---
theme: default
colorSchema: dark
layout: center
class: bg-black text-white
---

# Google I/0开发者大会
2025/8/13 上海·世博中心

---
theme: default
colorSchema: dark
layout: center
class: bg-black text-white
---

<h1 class="text-center pb-5">Google开发者大会</h1>

<div class="flex justify-between items-center w-full gap-5">
  <img class="w-1/3"src="/google-io-2025/1.jpg" alt="google io 1">
  <img class="w-1/3"src="/google-io-2025/2.jpg" alt="google io 2">
  <img class="w-1/3"src="/google-io-2025/3.jpg" alt="google io 3">
</div>

---
theme: default
colorSchema: dark
layout: center
class: bg-black text-white
class: bg-black text-white
---

<h1 class="text-center pb-5">Google开发者大会</h1>

<div class="flex justify-between items-center w-full gap-5">
  <img class="w-1/3"src="/google-io-2025/4.jpg" alt="google io 1">
  <img class="w-1/3"src="/google-io-2025/5.jpg" alt="google io 2">
  <img class="w-1/3"src="/google-io-2025/6.jpg" alt="google io 3">
</div>

---
theme: default
colorSchema: dark
layout: center
class: bg-black text-white
---

<h1 class="text-center pb-5">Google开发者大会</h1>

<div class="flex justify-between items-center w-full gap-5">
  <img class="w-1/3"src="/google-io-2025/7.jpg" alt="google io 1">
  <img class="w-1/3"src="/google-io-2025/8.jpg" alt="google io 2">
  <img class="w-1/3"src="/google-io-2025/9.jpg" alt="google io 3">
</div>

---
theme: default
colorSchema: dark
layout: center
class: bg-black text-white
---

<h1 class="text-center pb-5">Google开发者大会</h1>

<div class="flex justify-between items-center w-full gap-5">
  <img class="w-1/3"src="/google-io-2025/10.jpg" alt="google io 1">
  <img class="w-1/3"src="/google-io-2025/11.jpg" alt="google io 2">
  <img class="w-1/3"src="/google-io-2025/12.jpg" alt="google io 3">
</div>

---

# Android开发环境的搭建

---
---
theme: default
colorSchema: light
---

# 课程总览

<div class="h-[90%]">
  <ZoomableImage src="/android-brief/android-course-overview.png" />
</div>

---

<style>
.slidev-layout th {
  color: grey;
}

.slidev-layout td {
  padding-top: 0.3rem;
  padding-bottom: 0.3rem;
}
</style>

# 课时安排

<div class="flex flex-row items-start gap-2">

<div class="flex flex-1" v-click>

| 知识点 | 课时 |
|---|---|
| 软件开发预备知识 | 3 | 
| Java基础 | 6 | 
| 设计模式与常用数据结构 | 1 |
| Android开发环境搭建与IDE介绍 | 2 |
| gradle工具介绍 | 1 |
| Activity | 3 |
| UI组件 | 6 |
| Fragment | 3 |

</div>

<div class="flex flex-1" v-click>

| 知识点 | 课时 |
|---|---|
| Service | 2 |
| BroadcastReceiver | 2 |
| ContentProvider | 1 |
| 多媒体与动画 | 3 |
| 数据持久化 | 3 | 
| 网络访问 | 3 | 
| Jetpack | 9 |
| 综合实战项目 | 6 |

</div>

</div>

<div v-click class="mt-6 text-teal-700 flex">总共54课时</div>

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
  <li v-click>💻 查看android官方的文档：https://developer.android.google.cn/</li>
</ul>


<div v-click class="mb-2">📖 推荐阅读</div>

<div class="flex flex-row gap-2">
  <div class="flex flex-col justify-center items-center flex-1" v-click>
    <img src="/android-brief/android-book-dev-case.jpg" class="w-25 h-30 object-cover" />
    <div class="text-xs text-center">Android应用开发案例教程（Android Studio版）(第2版）</div>
  </div>
  <div class="flex flex-col justify-center items-center flex-1" v-click>
    <img src="/android-brief/android-book-java-core.jpg" class="w-25 h-30" />
    <div class="text-xs text-center">Java核心技术（第11版）</div>
  </div>
  <div class="flex flex-col justify-center items-center flex-1" v-click>
    <img src="/android-brief/android-book-first-line-of-code.jpg" class="w-25 h-30" />
    <div class="text-xs text-center">第一行代码（第三版）</div>
  </div>
  <div class="flex flex-col justify-center items-center flex-1" v-click>
    <img src="/android-brief/android-book-internal-deep.jpg" class="w-25 h-30" />
    <div class="text-xs text-center">深入理解Android内核设计思想（第2版 套装上下册）</div>
  </div>
  <div class="flex flex-col justify-center items-center flex-1" v-click>
    <img src="/android-brief/android-book-jetpack-compose.jpg" class="w-25 h-30" />
    <div class="text-xs text-center">Jetpack compose 从入门到实践</div>
  </div>
  <div class="flex flex-col justify-center items-center flex-1" v-click>
    <img src="/android-brief/android-book-kotlin-in-action.jpg" class="w-25 h-30" />
    <div class="text-xs text-center">Kotlin实战</div>
  </div>
</div>

---
theme: default
colorSchema: light
layout: center
---

# 预备知识

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

# Android开发环境的搭建 (1)

## 环境准备

- 一台安装windows的电脑，或者Macbook，Ubuntu desktop电脑
- 一台安卓手机，OS最好是android 6.0以上
- 统一使用Java语言开发

---

# Android开发环境的搭建 (2)

- android studio下载地址: https://developer.android.google.cn/studio?hl=zh-cn
- 课程AS统一使用版本: `Android Studio Narwhal Feature Drop | 2025.1.2 Patch 1`
  <img src="/android-brief/as-version.png" class="w-[60%]" />

--- 

# Android开发环境的搭建 (3)

- JDK使用AS默认的内置JDK (Jetbrain Runtime 21.0.6):
  <img src="/android-brief/jdk-version.png" class="w-[60%]" />
---

# Android开发环境的搭建 (4)

## AS推荐配置

- 代码风格Code Style设置为: <a href="/android-brief/GoogleStyle.xml" download>GoogleStyle.xml</a>
  <img src="/android-brief/as-code-style.png" class="w-[60%]" />

---

# Android开发环境的搭建 (5)

## AS推荐配置

- 保存自动格式化代码
  <img src="/android-brief/as-format-on-save.png" class="w-[60%]" />

---

# Android开发环境的搭建 (6)

## AS推荐配置

- 安装本次课程需要的插件plugins: `lombok`, `SimpleSqliteBrowser`
  <img src="/android-brief/as-useful-plugins.png" class="w-[60%]" />

---

# Android开发环境的搭建 (6)

## AS推荐配置

- SDK Manager是一个管理不同版本的安卓系统的sdk的工具，通过sdk manager，我们可以下载不同版本的android sdk, 源码
- 如果你没有安卓手机，可提前下载Android 16 SDK，并通过模拟器来运行app
  <img src="/android-brief/as-sdk-manager.png" class="w-[50%]" />

---

# Android开发环境的搭建 (7)

## Android模拟器

- Device Manager是一个管理安卓模拟器的工具，通过DM，我们可以创建多个不同版本的模拟器，查看模拟器内的文件等。
- 创建android模拟器
  <img src="/android-brief/as-device-manager.png" class="w-[60%]" />

---

# 软件开发的工程问题 - 版本号

<div class="flex items-center justify-center mb-5" v-click>
  <img src="/android-brief/software-semantic-version.png" class="w-[80%] !border-0"  />
</div>

- X: <u class="text-red-500">大版本号，一般不向下兼容</u>
- Y: <u class="text-orange-600">次版本号，功能扩展，且向后兼容</u>
- Z: <u class="text-lime-700">修订版本号，修复bug，且向后兼容</u>

---

# 软件开发的工程问题 - 版本号

## 参考 {v-click}

<ol v-click>
  <li>https://semantic-versioning.org/</li>
  <li>https://zhuanlan.zhihu.com/p/508354840</li>
  <li>https://www.baeldung.com/cs/semantic-versioning</li>
</ol>

---

# 软件开发的工程问题 - 版本控制
版本控制的王者::Git::

## 🤔 思考如下问题: {v-click="1"}

<div class="mb-5"></div>

<ul>
  <li v-click="2">
    什么是软件版本控制？
    <div class="text-red-800" v-click="4">版本控制软件用于记录代码的完整修改历史，记录修改了哪一行代码？谁修改了代码？修改代码的时间。</div>
  </li>
  <li v-click="3">
    为什么需要使用版本控制？不使用版本控制，会带来什么问题？
    <ol class="text-blue-800">
      <li v-click="5">多人协作开发，互不干扰。</li>
      <li v-click="6">错误恢复，随意在版本链条中回退或前进</li>
      <li v-click="7">实现软件代码的可追踪，可溯源</li>
      <li v-click="8">多功能/bug修复迭代开发，多分支管理</li>
    </ol>
  </li>
</ul>

---

# 软件开发的工程问题 - 软件生命流程

### 💦 瀑布开发流程 {v-click}

<div v-click class="flex justify-center my-6">  
需求分析 ➡ 系统设计 ➡ 开发实现 ➡ 软件测试与集成 ➡ 软件发布 ➡ 软件维护
</div>

### 👨‍🏫 Agile敏捷开发 {v-click}

<ul class="my-6 ml-4">
  <li v-click>把一个项目拆开为多个迭代或冲刺（sprint），特点是功能小，开发到发布要快</li>
  <li v-click>增量交付，快速实现客户的需求</li>
  <li v-click>强调客户深度参与，构建跨职能团队（开发人员、测试、设计师，产品负责人）</li>
</ul>

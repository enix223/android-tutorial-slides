---
theme: android-theme
colorSchema: light
---

# 课程总览

<div class="h-[90%]">
  <ZoomableImage src="/android-brief/android-course-overview.png" />
</div>

---

<style>
  .qrcode_container {
    display: flex;
    justify-content: center;
  }
</style>

# 课程大作业
从零开始构建一个食谱app

<div class="flex flex-row gap-2">
  <img src="/android-brief/android-final-project.png" class="flex flex-1 w-1/2" />
  <div class="flex flex-1 flex-col items-cetner justify-center w-1/2 gap-2">
    <div class="flex justify-center items-center text-gray-600">UI设计图</div>
    <div class="flex break-all text-center text-gray-600">https://www.figma.com/design/5fxk2DPOpNYOzpGVsAyIK3/%E7%88%B1%E5%8E%A8%E6%88%BF?node-id=0-1&t=Iwidh2WjepqCAkcH-1</div>
    <QRCode
      value="https://www.figma.com/design/5fxk2DPOpNYOzpGVsAyIK3/%E7%88%B1%E5%8E%A8%E6%88%BF?node-id=0-1&t=Iwidh2WjepqCAkcH-1"
      :width="180"
      :height="180"
      color=""
      image=""
    />
  </div>
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
theme: android-theme
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

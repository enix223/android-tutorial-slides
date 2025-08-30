# 软件开发的工程问题 - 版本号

<div class="flex items-center justify-center mb-5" v-click>
  <img src="/android-brief/software-semantic-version.png" class="w-[80%] !border-0"  />
</div>

<div v-click>

- X: <u class="text-red-500">大版本号，一般不向下兼容</u>
- Y: <u class="text-orange-600">次版本号，功能扩展，且向后兼容</u>
- Z: <u class="text-lime-700">修订版本号，修复bug，且向后兼容</u>

</div>

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

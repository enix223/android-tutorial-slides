---
theme: default
colorSchema: light
layout: center
---

# 了解Android Studio界面

---

# Android Studio界面组成

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/as-new-open.png" class="w-full" />
    </div>
    <div class="flex flex-1">
    <ol>
        <li v-click>1️⃣ New Project: 新建一个新的项目</li>
        <li v-click>2️⃣ Open: 打开一个原有的项目</li>
        <li v-click>3️⃣ 快速去打开最近的项目</li>
    </ol>
    </div>
</div>

---

# 新建项目 - 步骤1

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/as-new-step1.png" class="w-full" />
    </div>
    <div class="flex flex-1">
    <ol>
        <li v-click>1️⃣ No Activity: 表示<span class="text-blue-600">不创建默认的activity</span>，只是创建项目的基础框架。</li>
        <li v-click>2️⃣ Empty Activity: 表示创建项目的基础框架的同时, 创建一个<span class="text-blue-600">默认的activity</span>。</li>
    </ol>
    </div>
</div>

---

# 新建项目 - 步骤2

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/as-new-step2.png" class="w-full" />
    </div>
    <div class="flex flex-1">
    <ul>
        <li v-click>1️⃣ Name: 创建的项目名称，一般填写英文字符</li>
        <li v-click>2️⃣ Package Name: 应用的包名，必须为英文字符，中间用“.”连接，一般是按域名的逆序，例如: com.zhangsan.myapp</li>
        <li v-click>3️⃣ Save Location: 项目保存的目录地址</li>
        <li v-click>4️⃣ Minimum SDK: 项目支持的最低的安卓版本</li>
        <li v-click>5️⃣ Build configuration language: 构建工具的语言，选择Groovy DSL即可</li>
    </ul>
    </div>
</div>

---

# Android Studio界面组成

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/as-window-component.png" class="w-full" />
    </div>
    <div class="flex flex-1">
    <ol>
        <li v-click>工具栏：执行各种操作，其中包括运行应用和启动 Android 工具。</li>
        <li v-click>导航栏：在项目中导航，以及打开文件进行修改。此区域提供 Project 窗口中所示结构的精简视图。</li>
        <li v-click>编辑器窗口：创建和修改代码。编辑器可能因当前文件类型而异。例如，查看布局文件时，该编辑器会显示布局编辑器。</li>
        <li v-click>工具窗口栏：使用 IDE 窗口外部的按钮可展开或收起各个工具窗口。</li>
        <li v-click>工具窗口：执行特定任务，例如项目管理、搜索和版本控制等。您可以展开和折叠这些窗口。</li>
        <li v-click>状态栏：显示项目和 IDE 本身的状态以及任何警告或消息。</li>
    </ol>
    </div>
</div>

---

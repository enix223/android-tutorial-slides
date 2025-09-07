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

# 运行第一个安卓APP

<div class="flex flex-col gap-2">
    <div class="flex flex-1">
        <img src="/as-run-app.png" />
    </div>
    <div class="flex flex-1">
    <ol>
        <li v-click><span class="text-blue-600">选择设备</span>：如果你有多个手机或者虚拟机，那么你可以在设备列表选择需要在哪个设备运行</li>
        <li v-click><span class="text-blue-600">运行模式</span>：以普通运行模式安装并运行app</li>
        <li v-click><span class="text-blue-600">调试模式</span>：以调试模式运行app，如果需要断点调试，必须使用此模式运行</li>
    </ol>
    </div>
</div>

---

# 运行第一个安卓APP - 效果图

<img src="/as-run-app2.png" class="h-[90%]" />

---

# 如何查看日志

<div class="flex flex-col gap-2">
    <div class="flex">
        <img src="/as-logcat.png" class="w-[70%]" />
    </div>
    <div class="flex">
    <ol>
        <li v-click><span class="text-blue-600">打开logcat</span>：logcat界面可以让我们查看app运行的日志信息。方便我们进行调试。</li>
        <li v-click><span class="text-blue-600">日志界面</span>：日志包括了打印时间，日志产生的包名，开发者输出的消息</li>
        <li v-click><span class="text-blue-600">选择手机</span>：如果我们有多个手机，这里可以从下拉列表选择不同的手机，加载不同手机的日志</li>
    </ol>
    </div>
</div>

---

# 如何单步调试（1）

<div class="flex flex-col gap-2">
    <div class="flex">
        <img src="/as-debug-1.png" class="w-[70%]" />
    </div>
    <div class="flex">
    <ol>
        <li v-click><span class="text-blue-600">选择文件</span>：选择我们需要调试的代码所在的文件</li>
        <li v-click><span class="text-blue-600">打断点</span>：找到我们希望运行后，需要停止的位置，并在行号旁边单击，产生一个红色的断点。</li>
        <li v-click><span class="text-blue-600">运行调试</span>：点击调试按钮，开始调试</li>
    </ol>
    </div>
</div>

---

# 如何单步调试（2）

<div class="flex flex-col gap-2">
    <div class="flex">
        <img src="/as-debug-2.png" class="w-[60%]" />
    </div>
    <div class="flex">
    <ul>
        <li v-click><span class="text-blue-600">1️⃣</span>：表示断点暂停后，接着继续运行。</li>
        <li v-click><span class="text-blue-600">2️⃣</span>：如果遇到函数调用，直接执行并跳到下一行</li>
        <li v-click><span class="text-blue-600">3️⃣</span>：如果遇到函数调用，则调入函数体内</li>
        <li v-click><span class="text-blue-600">4️⃣</span>：停止调试</li>
    </ul>
    </div>
</div>

---

# 如何单步调试（3）

<img src="/as-debug-3.png" class="w-[60%]" />

调试过程，我们可以通过调试窗口的Variable查看当前运行上下文的变量值信息。例如，本例子中，代码停`SingleInstanceActivity`在第12行，此时的运行上下文包括如下变量：
* `savedInstanceState = null`

---

# 如何打包APK (1)

<p></p>

当我们开发完成app后，希望打包一个APK文件，然后上架到应用商店，或者发给其他人安装，我们可以通过如下流程打包APK

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/as-deploy-0.png" class="w-[50%]"/>
    </div>
    <div class="flex flex-1">
    <ul>
        <li v-click><span class="text-blue-600">1️⃣</span>：选择Build Variant选项卡</li>
        <li v-click><span class="text-blue-600">2️⃣</span>：Active Build Variant选择<span class="text-red-600">Release</span></li>
    </ul>
    </div>
</div>

---

# 如何打包APK (2)

<p></p>

当我们开发完成app后，希望打包一个APK文件，然后上架到应用商店，或者发给其他人安装，我们可以通过如下流程打包APK

<div class="flex flex-col gap-2">
    <div class="flex">
        <img src="/as-deploy-1.png" class="w-[60%]" />
    </div>
    <div class="flex">
    <ul>
        <li v-click><span class="text-blue-600">1️⃣</span>：选择工具栏Build菜单</li>
        <li v-click><span class="text-blue-600">2️⃣</span>：选择Generate App Bundles APKs</li>
        <li v-click><span class="text-blue-600">3️⃣</span>：选择Generate APKs</li>
    </ul>
    </div>
</div>

---

# 如何打包APK (3)

<p></p>

<img src="/as-deploy-2.png" class="w-[60%]" />

---

# 如何打包APK (4)

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/as-deploy-4.png" class="w-[90%]" />
    </div>
    <div class="flex flex-1">
    <ul>
        <li v-click><span class="text-blue-600">key store path</span>：选择证书保存的地址</li>
        <li v-click><span class="text-blue-600">password</span>：输入你的证书密码</li>
        <li v-click><span class="text-blue-600">alias</span>：填入证书的名称，例如app</li>
        <li v-click><span class="text-blue-600">First and last name</span>：填入你的名字</li>
        <li v-click><span class="text-blue-600">Organization Unit</span>：填入你部门</li>
        <li v-click><span class="text-blue-600">Organization</span>：填入你公司</li>
        <li v-click><span class="text-blue-600">City</span>：填入你所在城市</li>
        <li v-click><span class="text-blue-600">Province</span>：填入你所在省份</li>
        <li v-click><span class="text-blue-600">Country Code</span>：填入CN</li>
    </ul>
    </div>
</div>

---

# 如何打包APK (5)

<p></p>

<img src="/as-deploy-5.png" class="w-[60%]" />

---

# 如何打包APK (6)

<p></p>

<img src="/as-deploy-6.png" class="w-[60%]" />

<ul>
<li v-click>Build Variants选择"release"</li>
<li v-click>点击"Create"</li>
</ul>

---

# 如何打包APK (7)

<p></p>

<img src="/as-deploy-7.png" class="w-[60%]" />

当生成apk成功后，点击右下角的弹框，<span class="text-blue-700">locate</span>，打开apk所在文件夹，或者可以直接打开项目的子目录:

`app/build/output/apk/release/app-release.apk`

# Activity的创建
创建activity和对应的布局，并注册该activity

<div class="flex flex-row gap-1">
    <div class="flex flex-1">
        <img src="/activity-create-1.png" />
    </div>
    <div class="flex flex-1">
        <ol>
            <li>打开app/java，并右击包名</li>
            <li>选择菜单[New > Activity > Empty Views Activity]</li>
        </ol>
    </div>
</div>

---

# Activity的创建

<div class="flex flex-row gap-1">
    <div class="flex flex-1">
        <img src="/activity-create-2.png" />
    </div>
    <div class="flex flex-1">
        <ol>
            <li>Activity Name输入我们新建的activity类名，例如MainActivity</li>
            <li>勾选Generate a Layout file</li>
            <li>Layout Name填写我们新建的activity的布局文件名</li>
            <li>勾选Launcher Activity，把新建的activity设置为APP启动主页面</li>
        </ol>
    </div>
</div>

---

# Activity的创建

<p></p>

一个完整的Activity需要如下三个元素才能正常运作：

1. <span class="text-blue-700">Activity类</span>，这个类是activity的核心，在该类中编写我们页面的页面逻辑。
2. <span class="text-blue-700">布局文件</span>，布局文件位于`res/layout/activity_xxx.xml`，该页面使用xml表达页面的布局。例如`LinearLayout`
3. <span class="text-blue-700">注册Activity</span>，新增的activity需要在AndroidManifest.xml文件中声明。
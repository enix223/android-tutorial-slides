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
            <li>勾选Generate a Layout file，创建activity时顺便创建布局文件</li>
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

---

# Activity的布局

<div class="flex flex-col gap-2">
    <img src="/ui-designer.png" class="w-[65%]" />
    <div class="flex">
        <ol>
            <li>切换设计面板布局 （code: 代码方式布局; split: 代码+可视化布局; design: 可视化布局）</li>
            <li>代码编写布局，基于xml语法，以控件嵌套方式添加布局</li>
            <li>可视化布局, 基于可视化拖拽控件，通过属性面板配置控件的属性</li>
        </ol>
    </div>
</div>

---

# XML语法

Activity的布局基于xml语法

XML是一种标签语言，用于描述数据的层级结构和节点的属性。

```xml
<!-- 包含子节点的标签 -->
<LinearLayout android:id="@+id/main">
    <!-- 不含子节点的标签 -->
    <TextView android:text="TextView" />
</LinearLayout>
```

* 每个标签必须有开始(`<XXX>`)与结束(`</XXX>`)，如果不含子节点，可以直接以`<XXX />`结束
* 标签以嵌套方式组织，形成一棵节点树
* 标签可以附带属性，例如上述例子中`LinearLayout`包含一个属性`android:id`，其取值为`@+id/main`

---

# 布局常用的组件

## 1. 容器组件

* `LinearLayout` 线性布局，水平布局或垂直布局。
* `FrameLayout` 以层叠的方式来布局，后一个元素叠放在上一个元素上面。
* `RelativeLayout` 相对布局，可以通过声明元素之间的相对位置关系来布局
* `ConstraintLayout` 约束布局，通过声明元素之间的约束关系来布局。
* `TableLayout` 表格布局，类似excel表格形式，通过行与列方式排列子节点。

## 2. 普通组件

* `TextView` 文本组件，显示文本。
* `Button` 按钮组件，可以接收用户的点击操作。
* `ImageView` 显示图片的组件
* `EditText` 用户输入框组件，接收用户的文本输入。

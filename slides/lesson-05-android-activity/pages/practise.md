# 课堂练习 - 1
练习如何创建和定义activity，并添加基础的布局。

* 创建一个Activity: <span class="text-blue-700">MainActivity</span>
* 在AndroidManifest.xml中注册这个activity。
* 给MainActivity添加布局LinearLayout。
* 在LinearLayout中添加一个Button和TextView
* 加分项：添加生命周期回掉函数，并在回掉函数中输出日志，显示activity的各个生命阶段。

<div class="text-2xl mt-5">课堂PPT</div>
<div class="mt-3">
    <QRCode
        value="http://course.cloudesk.top"
        :width="180"
        :height="180"
        color=""
        image=""
    />
</div>

---

# 课堂练习 - 2
练习activity之间的跳转

* 创建两个Activity：<span class="text-blue-700">MainActivity</span>和<span class="text-blue-700">HomeActivity</span>。
* 分别添加两个Activity的布局<span class="text-blue-700">LinearLayout</span>
* MainActivity的布局中增加一个<span class="text-blue-700">按钮</span>，添加点击事件，点击后跳转到HomeActivity
* 加分项：分别设置MainActivity的启动模式为`standard`, `singleTop`, `singleTask`, `singleInstance`，并在HomeActivity中跳转MainActivity。

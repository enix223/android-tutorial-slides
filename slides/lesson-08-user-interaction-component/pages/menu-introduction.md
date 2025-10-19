
# Android Menu

<ul class="list-disc ml-5">
    <li>在 Android 中，Menu（菜单） 是一种用于为用户提供额外操作选项的 UI 组件。它常用于应用的操作栏（ActionBar / Toolbar）、悬浮菜单或长按弹出菜单等位置。菜单可以提高应用的可用性和结构性，让主要界面保持简洁。</li>
    <li>Android中的菜单有如下几种：
        <ul class="list-disc ml-5" style="width: 700px;">
            <li>OptionMenu：选项菜单，android中最常见的菜单，通过Menu键来调用</li>
            <li>SubMenu：子菜单，android中点击子菜单将弹出一个显示子菜单项的悬浮框， 子菜单不支持嵌套，即不能包括其他子菜单</li>
            <li>ContextMenu：上下文菜单，通过长按某个视图组件后出现的菜单，该组件需注册上下文菜单</li>
        </ul>
    </li>
</ul>


- 而加载菜单的方式有两种，
    1. 是直接通过编写菜单XML文件(在res目录创建menu文件夹，创建menu_main文件)，然后调用：
        getMenuInflater().inflate(R.menu.menu_main, menu);加载菜单 
    2. 通过代码动态添加，onCreateOptionsMenu的参数menu，调用add方法添加 菜单，add(菜单项的组号，ID，排序号，标题)，另外如果排序号是按添加顺序排序的话都填0即可！

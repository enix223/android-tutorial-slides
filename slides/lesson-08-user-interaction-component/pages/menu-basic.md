# 如何使用OptionMenu？


- 非常简单，重写两个方法就好，其实这两个方法我们在创建项目的时候就会自动生成~ 他们分别是：

```java
public boolean onCreateOptionsMenu(Menu menu)：调用OptionMenu，在这里完成菜单初始化
public boolean onOptionsItemSelected(MenuItem item)：菜单项被选中时触发，这里完成事件处理
```

- 当然除了上面这两个方法我们可以重写外我们还可以重写这三个方法：

```java
public void onOptionsMenuClosed(Menu menu)：菜单关闭会调用该方法
public boolean onPrepareOptionsMenu(Menu menu)：选项菜单显示前会调用该方法， 可在这里进行菜单的调整(动态加载菜单列表)
public boolean onMenuOpened(int featureId, Menu menu)：选项菜单打开以后会调用这个方法
```



---


# 如何使用ContextMenu？


1. 重写onCreateContextMenu()方法

```java
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)： 
当用户长按已注册的 View 时调用，用于创建（初始化）上下文菜单，
通常在此通过 menuInflater.inflate() 加载菜单资源文件。
```

2. 为view组件注册上下文菜单，使用registerForContextMenu()方法,参数是View

```java
public void registerForContextMenu(View view)：为指定的 View 注册上下文菜单，
当用户长按该 View 时会触发上下文菜单。
```

3. 重写onContextItemSelected()方法为菜单项指定事件监听器

```java
public boolean onContextItemSelected(MenuItem item)： 
当上下文菜单中的某个菜单项被点击时调用，
在此方法中根据 item.getItemId() 判断并执行对应的操作逻辑。
```
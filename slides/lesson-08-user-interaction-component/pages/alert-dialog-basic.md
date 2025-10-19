
- AlertDialog 不同于前面已经学习过的 UI 控件，它不能用 new 方法创造出来，也不能用 XML 创建我们只能通过 AlertDialob 的内部类 Builder 来创建

    ```java
    AlertDialog.Builder(Context context)
    AlertDialog.Builder(Context context, int themeResId)
    ```

- 然后调用 AlertDialog 的一些方法进行定制，最后调用 show() 方法来显示，所以，创建一个 AlertDialog 的基本流程是

    1. 创建 AlertDialog.Builder 对象

    ```java
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    ```

    2. 调用 setIcon() 设置图标， setTitle() 或 setCustomTitle() 设置标题

    3. 设置对话框的内容 setMessage()

    4. 调用 setPositive/Negative/NeutralButton() 设置 确定，取消，普通 按钮

    5. 调用 create() 方法创建这个对象，再调用 show() 方法将对话框显示出来



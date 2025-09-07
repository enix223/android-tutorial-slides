
# 输入输出流

- Java I/O（输入/输出）是 Java 处理数据输入输出的核心 API。java.io 包提供了用于读写数据的各种类和接口。

- I/O 流的概念

- I/O 流是数据传输的抽象，可以看作是一个连续的数据流。根据数据流向，可以分为：

  输入流 - 从数据源读取数据

  输出流 - 向目的地写入数据

- 常用 I/O 流的分类：

---

## <img src="/IO.png"  />

---

```java
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("输入名字: ");
            String name = reader.readLine();
            System.out.println("你好 " + name);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：编写一个简单的用户信息收集程序：

1. 询问用户的姓名、年龄、爱好
2. 读取用户的每次输入
3. 最后汇总显示用户信息
</div>

---

```java
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("=== 用户信息收集 ===");

            System.out.print("请输入您的姓名: ");
            String name = reader.readLine();

            System.out.print("请输入您的年龄: ");
            String age = reader.readLine();

            System.out.print("请输入您的爱好: ");
            String hobby = reader.readLine();

            System.out.println();
            System.out.println("=== 您的信息 ===");
            System.out.println("姓名: " + name);
            System.out.println("年龄: " + age);
            System.out.println("爱好: " + hobby);
            System.out.println("感谢您的参与！");
        } catch (IOException e) {
            System.out.println("输入输出错误: " + e.getMessage());
        }
    }
}
```
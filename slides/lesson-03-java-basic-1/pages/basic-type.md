

# 基本类型

| 类型    | 字节数 | 默认值   | 示例    |
| ------- | ------ | -------- | ------- |
| byte    | 1      | 0        | 100     |
| short   | 2      | 0        | 1000    |
| int     | 4      | 0        | 10000   |
| long    | 8      | 0L       | 100000L |
| float   | 4      | 0.0f     | 3.14f   |
| double  | 8      | 0.0      | 3.1415  |
| char    | 2      | '\u0000' | 'A'     |
| boolean | 1      | false    | true    |

---

# 示例代码

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int age = 18;
        double price = 99.99;
        boolean isActive = true;
        char grade = 'A';
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：声明三个变量，分别表示你的姓名（char[]）、年龄（int）、是否已学过 Java（boolean）。
</div>

---

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        char[] name = {'A','l','i','c','e'};
        int age = 20;
        boolean hasLearnedJava = true;
    }
}
```

# 异常处理 Exception

- 在 Java 中，异常处理是一种重要的编程概念，用于处理程序执行过程中可能出现的错误或异常情况

- Java 分为：

  检查型异常 (Checked Exception) → 必须处理或抛出

  非检查型异常 (Unchecked Exception) → 可不处理

```java
public class Main {
    public static void main(String[] args) {
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("除数不能为零");
        } finally {
            System.out.println("结束");
        }
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：写程序读取数组第 10 个元素
</div>

---

```java
public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        try {
            System.out.println(arr[9]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界");
        }
    }
}
```
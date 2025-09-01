
# Java8 流函数

- 什么是 Stream？

  数据管道：像流水线一样处理数据

  不会修改源数据：对原始集合没有影响

  惰性执行：只有需要结果时才真正计算

  一次性使用：每个 Stream 只能使用一次

- Stream API 提供函数式操作集合

- 支持 map, filter, reduce

---

# 示例代码

```java
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        List<Integer> squared = nums.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());

        squared.forEach(System.out::println);
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：统计成绩大于 80 的学生
</div>

---

```java
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> scores = Arrays.asList(70,85,90,60);
        long count = scores.stream().filter(s -> s > 80).count();
        System.out.println("大于80的成绩数量：" + count);
    }
}
```
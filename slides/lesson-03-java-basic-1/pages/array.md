
# 数组 Array

- 数组是存储相同类型元素的容器

- 数组长度固定

- 支持通过索引访问

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int[] nums = {1, 2, 3, 4};
        for(int n : nums) {
            System.out.println(n);
        }
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建一个长度为 5 的数组存储成绩，并求平均值
</div>

---

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int[] scores = {80, 90, 75, 85, 95};
        int sum = 0;
        for(int s : scores) {
            sum += s;
        }
        double average = sum / (double)scores.length;
        System.out.println("平均成绩：" + average);
    }
}
```

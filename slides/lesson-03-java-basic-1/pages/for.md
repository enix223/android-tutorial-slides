
# for 循环

- 已知次数循环

- 三部分：初始化；条件；更新

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        for(int i=1; i<=5; i++) {
            System.out.println("第 " + i + " 次");
        }
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：打印 1 到 100 的和
</div>

---

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int sum = 0;
        for(int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("总和：" + sum);
    }
}
```
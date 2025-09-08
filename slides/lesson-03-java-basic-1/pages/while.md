
# while 循环

- 条件为 true 时一直执行

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int i = 1;
        while(i <= 5) {
            System.out.println(i);
            i++;
        }
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：用 while 打印 1~10 的偶数
</div>

---

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int i = 1; // 从 1 开始
        while(i <= 10) {
            if(i % 2 == 0) {
                System.out.println(i);
            }
            i++;
        }
    }
}

```
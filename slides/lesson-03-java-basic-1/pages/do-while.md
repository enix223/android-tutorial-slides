
# do...while 循环

- 至少执行一次，再判断条件

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int i = 1;
        do {
            System.out.println(i);
            i++;
        } while(i <= 5);
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
 练习：用 do...while 打印 1~10 的偶数
</div>

---

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int i = 2;
        do {
            System.out.println(i);
            i += 2;
        } while (i <= 10);
    }
}
```
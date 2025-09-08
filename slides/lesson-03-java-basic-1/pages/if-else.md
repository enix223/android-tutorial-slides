
# if / else

- 根据条件决定代码分支
- 常用形式：单分支、双分支、多分支

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int age = 18;
        if(age >= 18) {
            System.out.println("成年人");
        } else {
            System.out.println("未成年人");
        }
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：判断一个数是否是偶数
</div>

---

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        int num = 10;
        if(num % 2 == 0) {
            System.out.println("偶数");
        } else {
            System.out.println("奇数");
        }
    }
}
```

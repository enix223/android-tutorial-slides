
# switch

- 多分支选择结构

- 适用于 有限个固定值 的判断

```java
public class Main {
    public static void main(String[] args) {
        int day = 3;
        switch(day) {
            case 1:
                System.out.println("周一");
                break;
            case 2:
                System.out.println("周二");
                break;
            case 3:
                System.out.println("周三");
                break;
            default:
                System.out.println("其他");
                break;
        }
    }
}
```

---

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：用 switch 判断成绩等级（A(优秀 (90-100分))/B(良好 (80-89分))/C(及格 (60-79分))/D(不及格 (60分以下))/default(无效的成绩等级)）
</div>

---

```java
public class Main {
    public static void main(String[] args) {
        char grade = 'B'; // 可以直接指定等级

        switch(grade) {
            case 'A':
                System.out.println("优秀 (90-100分)");
                break;
            case 'B':
                System.out.println("良好 (80-89分)");
                break;
            case 'C':
                System.out.println("及格 (60-79分)");
                break;
            case 'D':
                System.out.println("不及格 (60分以下)");
                break;
            default:
                System.out.println("无效的成绩等级");
        }
    }
}
```

# 多线程与同步

- 多线程允许程序同时执行多任务，多线程是多任务的一种特别的形式，但多线程使用了更小的资源开销

- Java 提供了三种创建线程的方法：

  通过实现 Runnable 接口；

  通过继承 Thread 类本身；

  通过 Callable 和 Future 创建线程。

```java
class MyThread extends Thread {
    public void run() {
        for(int i=0;i<5;i++) {
            System.out.println("Thread: " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new MyThread().start();
    }
}
```

---

# 线程生命周期

<div class="flex flex-col items-center justify-center">
  <img src="/java-thread.png" width="500" />
  <div class="mt-2 text-gray-600"></div>
</div>

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：两个线程打印奇偶数
</div>

---

```java
class PrintThread extends Thread {
    int start;
    PrintThread(int s) { start = s; }
    public void run() {
        for(int i=start;i<=10;i+=2){
            System.out.println(i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new PrintThread(0).start(); // 偶数线程
        new PrintThread(1).start();  // 奇数线程
        // 注意：输出顺序是不确定的，取决于线程调度
    }
}
```
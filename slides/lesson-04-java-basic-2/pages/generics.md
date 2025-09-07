

# 泛型 Generics

- Java 泛型是 JDK 5 中引入的一个新特性，泛型提供类型参数化，增加类型安全，避免强制类型转换

```java
class SimpleBox<T> {
    private T content;

    SimpleBox(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

public class Main {
    public static void main(String[] args){
        SimpleBox<String> stringBox = new SimpleBox<>("Hello Java");
        String message = stringBox.getContent();
        System.out.println(message);
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建 Box&lt;T&gt; 泛型类
</div>

---

```java
class Box<T> {
    private T value;
    Box(T value) {
        this.value = value;
    }
    public T get() {
        return value;
    }
}


public class Main {
    public static void main(String[] args) {
        Box<Integer> b = new Box<>(123);
        System.out.println(b.get());
    }
}
```
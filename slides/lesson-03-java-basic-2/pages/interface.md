
# 接口 Interface

- 接口在 JAVA 编程语言中是一个抽象类型，是抽象方法的集合，接口通常以 interface 来声明。一个类通过继承接口的方式，从而来继承接口的抽象方法

- Java 8 后接口可包含 default 和 static 方法

- 类通过 implements 实现接口

```java
interface Drawable {
    void draw();
    default void info() {
        System.out.println("This is drawable");
    }
}

class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：定义接口 Playable，方法 play()，实现类 Music 打印 "Playing music"
</div>

---

```java
interface Playable {
    void play();
}

class Music implements Playable {
    public void play() {
        System.out.println("Playing music");
    }
}

public class Main {
    public static void main(String[] args) {
        Playable m  = new Music();
        m.play();
    }
}

```
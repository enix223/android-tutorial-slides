
# 类与对象 Class

- 类：一个模板或蓝图，它描述了某一类对象共有的属性（状态） 和 行为（方法）。

- 对象：类的实例，拥有类的属性和行为

```java
class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student("Alice", 20);
        s.sayHello();
    }
}

```

---

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建一个 Car 类，属性有 brand、price，方法 start() 打印 "Car is starting"
</div>

---

```java
class Car {
    String brand;
    double price;

    Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    void start() {
        System.out.println("Car is starting");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 20000);
        car.start();
    }
}

```
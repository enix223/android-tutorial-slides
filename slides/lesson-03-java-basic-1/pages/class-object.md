# 什么是类

类是描述一大类事物或概念的模版，类包含了如下信息：

<v-clicks>

* 成员变量: 定义了状态信息，包括状态的类型，状态对外的可见性。
* 成员方法: 定义方法，可以是修改状态。
* 继承：类允许单继承，子类包含父类的属性。
* 多态：子类可以覆盖父类定义的方法，实现自己特有的方法

</v-clicks>

---

# 什么是类

<div class="flex flex-row gap-2">
    <div class="flex flex-1">
        <img src="/java-class.png" class="w-full" />
    </div>
    <div class="flex flex-1">
        <ul>
            <li v-click><span class="text-yellow-700">汽车Car</span>: 一个描述汽车的类，汽车都有一些共同属性，例如座位数、有多少个门</li>
            <li v-click><span class="text-red-700">燃油车 GasolineCar</span>: 是一个描述以汽油为燃料的汽车大类，燃油车属于汽车，所以拥有汽车的属性，同时也有属于它自己的属性，如排量</li>
            <li v-click><span class="text-green-700">电动汽车 ElectricCar</span>: 是一个描述以新能源为动力的汽车大类，电动汽车也属于汽车，所以拥有汽车的属性，同时也有属于它自己的属性，如纯电续航。</li>
        </ul>
    </div>
</div>

---

# 什么是对象

<img src="/java-object.png" class="w-[80%]" />

---

# 什么是对象

<p></p>

对象是类的实例，基于类模版生成的单个个体。每个对象拥有属于自己的属性值。

<v-clicks>

* 通过类初始化出来具体的对象
* 拥有属于自己的属性
* 所有对象的属性都来自于类的定义中

</v-clicks>

---

# 包 (package)

* 包的定义

    Java组织类的形式是通过包来管理，包对应目录层级，例如包: com.example.app

    ```
    /com/
        /example/
            /app/
                Main.java
                HelloWorld.java
            App.java
    ```

    java的类通过包名+类名标识一个独一无二的名字，例如上面的例子中`Main`类，它的完整标识符是

    ```
    com.example.app.Main
    ```

* 包的导入

    例如我们需要在`App.java`文件中导入`Main`这个类

    ```java
    import com.example.app.Main;
    ```

---

# 类的创建

1. 创建一个包对应的目录

    ```
    /com/example/app/
    ```

2. 在包中创建一个Java文件

    ```
    /com/example/app/Main.java
    ```

3. 在Main.java文件中定义一个类Main (<span class="text-red-500">注意: java文件中的类名必须与文件名一致，且只能有一个public的顶级的类</span>)

    ```java
    public class Main {
        // 定义属性
        private String name;

        // 定义方法
        public void hello() {
            System.out.println(name);
        }
    }
    ```

---

# 类的可见性

类有两种访问权限

* public class: 允许包外的其他类访问
* class: 只允许同一个包中的其他类访问

## 例子

我们有一个java项目

```
/com/
    /example/
        /app/
            Main.java
            /model/
                Car.java
                GasolineCar.java
```

---

# 类的可见性

* Car.java
  
```java
package com.example.app.model;

// Engine类只对com.example.app.model可见
class Engine {
}

// Car类对任何外部类均可见
public class Car {
}
```

* Main.java

```java
package com.example.app;

import com.example.app.model.Car;

public class Main {
    // 允许导入外部包的public类
    // 不允许导入Engine类
    private Car car;
}
```

---

# 属性和方法的可见性

* private: 私有属性，只能在类内部访问该属性
* protected: 保护属性，允许在本类内部和子类访问该属性
* public: 公开属性，允许类的外部访问该属性

```java
public class Car {
    // 私有属性，只允许Car类的内部访问
    private int seats;

    // 保护属性，允许Car类的内部和Car的子类访问
    protected String name;

    // 公开属性，允许外部类直接读取和修改属性
    public int wheels;

    private void print() { // 只允许类内部调用
    }

    protected void init() { // 允许Car类和Car子类调用
    }

    public void start() { // 允许所有外部类调用
    }
}
```

---

# 父类定义 - Car

```java
public class Car {
    private int numOfSeats;
    private int numOfDoors;

    public Car(int numOfSeats, int numOfDoors) {
        this.numOfSeats = numOfSeats;
        this.numOfDoors = numOfDoors;
    }

    public void start() {
        System.out.printf("我有%d个座位, %d个门\n", numOfSeats, numOfDoors);
    }
}
```

---

# 子类定义 - GasolineCar和ElectricCar

```java
public class GasolineCar extends Car {
    private float displacement;

    public GasolineCar(int numOfSeats, int numOfDoors, float displacement) {
        super(numOfSeats, numOfDoors)
        this.displacement = displacement;
    }
}
```

```java
public class ElectricCar extends Car {
    private int cltc;

    public ElectricCar(int numOfSeats, int numOfDoors, int cltc) {
        super(numOfSeats, numOfDoors)
        this.cltc = cltc;
    }
}
```

---

# 创建对象

```java
public class MainActivity extends AppCompactActivity {
    public void main() {
        GasolineCar s = new GasolineCar(5, 4, 2.0);
        s.start();
    }
}
```

---

# 课堂练习

1. 通过Android Studio创建一个新项目
2. 以本课堂中汽车的类为例子，分别创建`Car`类，`GasolineCar`类, `ElectricCar`类，并定义属性和方法
3. 在`MainActivity`中创建对应的对象，并调用`start`方法。

<div class="text-2xl mt-5">课堂PPT</div>

<div class="mt-3">
    <QRCode
        value="http://course.cloudesk.top"
        :width="180"
        :height="180"
        color=""
        image=""
    />
</div>

# 泛型 Generics

- Java 泛型是 JDK 5 中引入的一个新特性，泛型提供类型参数化，增加类型安全，避免强制类型转换

### 🤔 为什么需要范型

例如我要写一个加法函数，实现多种数字类型的加法

<v-click>

* 整数的加法

```java
public class IntAdder {
    public int add(int a, int b) {
        return a + b;
    }
}
```

</v-click>

<v-click>

* 浮点数的加法

```java
public class FloatAdder {
    public float add(float a, float b) {
        return a + b;
    }
}
```

</v-click>

<v-click>


> 函数内部逻辑都一样，只是数据的类型不一样，此时，我们就可以使用范型来解决重复编码的问题。

</v-click>

---

# 泛型 Generics
ArrayList就是范型的最佳例子。ArrayList可以存储不同的数据类型，存储和获取元素的方法都一样


```java
public class ArrayList<T> {
    private T[] data;
    private int size = 0;

    public ArrayList(int size) {
        this.data = new T[size];
        this.size = 0;
    }

    public T get(int i) {
        return data[i];
    }

    public void add(T item) {
        int i = size ++;
        data[i] = item;
    }
}
```
---

# 泛型 Generics

```java
public class Main {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        int i = list.get(0);
        System.out.println("第一个数字是: %d", i);
    }
}
```

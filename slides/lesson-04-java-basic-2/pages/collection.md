
# 集合框架

- 为什么需要集合框架？(早期的困境)

- 在 Java 早期版本中，存在一些独立的数据结构类：

  Vector - 动态数组、Hashtable - 哈希表

  Stack - 栈结构、Properties - 配置存储

  Dictionary - 抽象键值对存储

- 存在的问题：

  每个类都有自己独特的方法命名和 API 设计

  学习成本高，需要记住不同类的不同方法

  代码复用性差，难以在不同数据结构间转换

  缺乏统一的编程接口和设计理念

---

- 集合框架的设计理念、核心目标

  统一性 - 提供一套标准接口，让所有集合类遵循相同的规范

  高性能 - 确保基本数据结构的实现都是高效的

  互操作性 - 不同集合类型可以相互协作和转换

  扩展性 - 易于扩展和自定义新的集合实现

---

# 集合接口

  <img src="/collection_interface.png"/>

---

# Collection 接口

- Collection 是一个根接口，我们常用其子接口（如 List）的实现类，例如 ArrayList

```java
import java.util.ArrayList;
import java.util.Collection;

public class CollectionExample {
public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>(); // 创建Collection实例

        collection.add("Apple"); // 添加元素
        collection.add("Banana");
        collection.add("Orange");
        collection.add("Apple"); // 允许重复

        System.out.println("集合元素:");
        for (String fruit : collection) { // 遍历集合
            System.out.println(fruit);
        }

        System.out.println("集合大小: " + collection.size());  // 其他操作
        System.out.println("包含Banana: " + collection.contains("Banana"));

```

---

```java
        collection.remove("Orange");  // 删除元素
        System.out.println("删除后大小: " + collection.size());
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建一个Collection集合，添加5个数字，遍历并计算它们的总和
</div>

---

```java
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println("总和: " + sum);
    }
}

```

---

# List 接口

- 常用实现类：ArrayList

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(); // 创建List实例

        list.add("Java"); // 添加元素
        list.add("Python");
        list.add("C++");
        list.add(1, "JavaScript"); // 在指定位置插入

        System.out.println("第二个元素: " + list.get(1));  // 访问元素
        System.out.println("Python的位置: " + list.indexOf("Python"));

        list.set(2, "Go");  // 修改元素

        System.out.println("列表元素:");
        for (int i = 0; i < list.size(); i++) { // 遍历列表
            System.out.println((i + 1) + ". " + list.get(i));
        }

```

---

```java
        List<String> subList = list.subList(1, 3); // 子列表
        System.out.println("子列表: " + subList);
    }
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建一个String类型的List，添加5个名字，找出最长的名字并打印
</div>

---

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Christopher");
        names.add("David");
        names.add("Emma");

        String longestName = "";
        for (String name : names) {
            if (name.length() > longestName.length()) {
                longestName = name;
            }
        }
        System.out.println("最长的名字: " + longestName);
    }
}
```

---

# Set 接口

- 常用实现类：HashSet

```java
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>(); // 创建Set实例

        set.add("Apple");  // 添加元素（自动去重）
        set.add("Banana");
        set.add("Orange");
        set.add("Apple"); // 这个不会被添加

        System.out.println("Set元素:");
        for (String fruit : set) {  // 遍历集合
            System.out.println(fruit);
        }

        Set<String> otherSet = new HashSet<>(); // 集合操作
        otherSet.add("Banana");
        otherSet.add("Grape");

```

---

```java
        Set<String> union = new HashSet<>(set);  // 并集
        union.addAll(otherSet);
        System.out.println("并集: " + union);

        Set<String> intersection = new HashSet<>(set);  // 交集
        intersection.retainAll(otherSet);
        System.out.println("交集: " + intersection);
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建两个Set集合，分别包含一些数字，找出它们的交集和并集
</div>

---

```java
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // 交集
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("交集: " + intersection);

        // 并集
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("并集: " + union);
    }
}

```

---

# Map 接口

- 常用实现类：HashMap

```java
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> studentScores = new HashMap<>();  // 创建Map实例

        studentScores.put("Alice", 95); // 添加键值对
        studentScores.put("Bob", 88);
        studentScores.put("Charlie", 92);
        studentScores.put("Alice", 98); // 更新Alice的分数

        System.out.println("Alice的分数: " + studentScores.get("Alice")); // 获取值

        System.out.println("学生成绩:");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) { // 遍历Map
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("包含David: " + studentScores.containsKey("David"));  // 检查键值是否存在
        System.out.println("包含分数100: " + studentScores.containsValue(100));

```

---

```java
        System.out.println("所有学生: " + studentScores.keySet()); // 获取所有键和值
        System.out.println("所有分数: " + studentScores.values());
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建一个Map，存储商品名称和价格，找出价格最高的商品
</div>

---

```java

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Double> products = new HashMap<>();
        products.put("Laptop", 1200.0);
        products.put("Phone", 800.0);
        products.put("Tablet", 500.0);
        products.put("Headphones", 150.0);

        String mostExpensive = "";
        double maxPrice = 0;
        for (Map.Entry<String, Double> entry : products.entrySet()) {
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
                mostExpensive = entry.getKey();
            }
        }
        System.out.println("最贵的商品: " + mostExpensive + " - $" + maxPrice);
    }
}

```

---

# Iterator 接口

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(); // 创建列表
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        Iterator<Integer> iterator = numbers.iterator(); // 获取迭代器

        System.out.println("使用迭代器遍历:");
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println(number);

            if (number > 30) {  // 删除大于30的元素
                iterator.remove();
            }
        }

```

---

```java
        System.out.println("删除后的列表: " + numbers);

        Iterator<Integer> iterator2 = numbers.iterator(); // 使用forEachRemaining (Java 8+)
        System.out.println("使用forEachRemaining:");
        iterator2.forEachRemaining(System.out::println);
    }
}

```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：创建一个List包含一些字符串，使用Iterator删除所有长度小于3的字符串
</div>

---

```java

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hi");
        words.add("Hello");
        words.add("Java");
        words.add("OK");
        words.add("No");

        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (word.length() < 3) {
                iterator.remove();
            }
        }
        System.out.println("处理后的列表: " + words);
    }
}

```

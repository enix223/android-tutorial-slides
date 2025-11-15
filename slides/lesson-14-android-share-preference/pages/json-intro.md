# JSON
JSON是JavaScript Object Natation, 一种轻量级的文本数据交换格式, 与XML一样, 广泛被采用的客户端和服务端交互的解决方案！具有良好的可读和便于快速编写的特性。

## JSON的特性

* JSON一种语言无关的标准，可用于java，javascript，python，甚至可以用于单片机中
* JSON可读性强，相对于二进制文件，我们可以不借助任何特殊的软件即可实现json的读写

## Json与XML的比较

* JSON和XML的数据可读性基本相同;
* JSON和XML同样拥有丰富的解析手段
* JSON相对于XML来讲，数据的体积小
* JSON对数据的描述性比XML较差

---

#  JSON的格式规范
JSON语法规则介绍

1. 键值对 (Key-Value Pairs)

```json
{
  "name": "张三",
  "age": 25,
  "isStudent": false
}
```

2. 值的数据类型

    * 字符串："hello"（必须双引号）
    * 数字：123、12.34
    * 布尔值：true、false
    * null：null
    * 对象：{ ... }
    * 数组：[ ... ]

---

# JSON对象

用花括号 `{}` 包裹，包含键值对：

```json
{
  "firstName": "李",
  "lastName": "四",
  "address": {
    "city": "北京",
    "street": "长安街"
  }
}
```

# JSON数组

用方括号 [] 包裹，包含有序的值列表：

```json
["苹果", "香蕉", "橙子"]
```

---

# JSON完整示例

```json
{
  "students": [
    {
      "id": 1,
      "name": "小明",
      "courses": ["数学", "英语"],
      "scores": {
        "数学": 95,
        "英语": 88
      }
    },
    {
      "id": 2,
      "name": "小红",
      "courses": ["语文", "物理"]
    }
  ],
  "totalCount": 2
}
```

---

# Android中的JSON工具类

这些API都存在于org.json包下，而我们用到的类有下面这些：

* JSONObject： Json对象，可以完成Json字符串与Java对象的相互转换

* JSONArray： Json数组，可以完成Json字符串与Java集合或对象的相互转换

* JSONStringer： Json文本构建类，这个类可以帮助快速和便捷的创建JSON text， 每个JSONStringer实体只能对应创建一个JSON text

* JSONTokener：Json解析类

* JSONException：Json异常

---

# 序列化与反序列化

* 序列化, 把一个java对象转换为JSON文本

    ```java
    new Person("张三", 18)
    ```

    转换为

    ```json
    {"name": "张三", "age": 18}
    ```


* 反序列化，把一个JSON文本转换为一个java对象

    ```json
    {"name": "张三", "age": 18}
    ```

    转换为

    ```java
    new Person("张三", 18)
    ```

---

# JSON反序列化

- 我们解析的是上面这个简单的Json，首先我们来写一个POJO类： Person.java


```java
public class Person {
    
    private String id;
    private String name;
    private String age;

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
```

---

```java

    public void setAge(String age) {
        this.age = age;
    }
    
    public String getAge() {
        return this.age;
    }
    
    @Override
    public String toString() {
        return this.name + "~年方：" + this.age;
    }
}
```

---

- 写一个解析上述Json字符串的方法：

```java
private void parseEasyJson(String json){
    persons = new ArrayList<Person>();
    try{
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Person person = new Person();
            person.setId(i+"");
            person.setName(jsonObject.getString("name"));
            person.setAge(jsonObject.getString("age"));
            persons.add(person);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

---

# 第三方JSON工具

*  fastjson/fastjson2，阿里巴巴出品，可以通过一行代码实现序列化和反序列化

    ```java
    // 序列化
    String json = JSON.toJSONString(person);

    // 反序列化
    Person person = JSON.parseObject("{\"name\": \"张三\"}", Person.class);
    ```
* gson, 谷歌出品，使用跟fastjson一样，也是非常简单

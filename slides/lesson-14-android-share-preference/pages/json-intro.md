# JSON
JavaScript Object Natation, 一种轻量级的数据交换格式, 与XML一样, 广泛被采用的客户端和服务端交互的解决方案！具有良好的可读和便于快速编写的特性。

 Json与XML的比较

* JSON和XML的数据可读性基本相同;

* JSON和XML同样拥有丰富的解析手段

* JSON相对于XML来讲，数据的体积小

* JSON与JavaScript的交互更加方便

* JSON对数据的描述性比XML较差

* JSON的速度要远远快于XML

---

#  Json的格式规范

* 就像协议一样，肯定是有一套规范的，毕竟双方都是通过Json字符串来传递数据，语法规则如下： 数据在名称/值对中；数据由逗号分隔；花括号保存对象；方括号保存数组； 而Json数据的书写格式：名称/值对 比如： "person"："coder-pig" 比如一个简单的Json字符串：

```java
[
    { "id":"1","name":"基神","age":"18" },
    { "id":"2","name":"B神","age":"18"  },
    { "id":"3","name":"曹神","age":"18" }
]

```


---


# Android给我们提供的Json解析类



  这些API都存在于org.json包下，而我们用到的类有下面这些：

* JSONObject： Json对象，可以完成Json字符串与Java对象的相互转换

* JSONArray： Json数组，可以完成Json字符串与Java集合或对象的相互转换

* JSONStringer： Json文本构建类，这个类可以帮助快速和便捷的创建JSON text， 每个JSONStringer实体只能对应创建一个JSON text

* JSONTokener：Json解析类

* JSONException：Json异常

---

# 代码示例：解析Json字符串：

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


<div class="flex flex-col items-center justify-center">
    <img src="/android-json.png" width="250"/>
</div>
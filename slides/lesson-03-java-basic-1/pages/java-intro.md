# Java语言简介
Java的诞生与实现

Java是一个为了实现一次编码到处运行的目标而创造的语言。Java编译后生成字节码，并通过JVM(Java virtual machine)解析并运行。Java可以理解为是一种编译+解析的语言。

<ul>
<li v-click><span class="text-blue-700">编译型语言</span>，以C, C++为代表，编译器把源代码直接编译为机器指令，优点是运行速度快，缺点编译后的文件就是不可移植。</li>
<li v-click><span class="text-blue-700">解析型语言</span>，以python，javascript为代表，需要安装解析器，解析器负责解析并执行源代码，优点是开发效率高，缺点是运行速度慢。</li>
<li v-click><span class="text-blue-700">混合型语言</span>，以Java为代表，通过JVM实现代码的可移植性，同时通过JIT热点代码编译为机器指令，实现更高的运行效率。</li>
</ul>

--- 

# 第一个Java程序

```java
package com.enixyu.app;

// 导入依赖
import java.util.Arrays;

/**
 * 这是多行代码注释
 * 注释可以写多行
 */
public class HelloWorld {
    public static void main(String[] args) {
        // 这是一行代码注释
        System.out.println("Hello World: " + Arrays.toString(args));
    }
}
```

<v-clicks>

* HelloWolrd: 每个java文件都必须至少有一个类，此例子为`HelloWorld`
* main: 定义了程序的入口函数，必须声明为`public static void main`
* args: 接收一个参数，名为args，我们可以通过`java`命令传递参数:
  ```shell
  java HelloWorld 参数1 参数2
  ```
* System.out.println: 这个println是System类中一个静态成员out的一个方法，跟c语言的`printf`功能类似。

</v-clicks>

# 1. Android Studio无法导入项目

* 问题: 

    ```
    打开项目后无法导入项目，左上角一直没有办法加载Android试图，代码也报错。
    ```

* 原因

    由于新建一个项目后，android studio需要下载gradle的编译工具，如果本地没有缓存，则需要从网上下载，外网访问不稳定，导致每次打开项目后，都无法正常加载。使用国内的镜像可以加快gradle工具的下载。

* 解决方法

    1. 打开文件: `gradle/wrapper/gradle-wrapper.properties`
    2. 删除如下行: 
        ```
        distributionUrl=https\://services.gradle.org/distributions/gradle-8.13-bin.zip
        ```
    3. 替换为:
        ```
        distributionUrl=https\://mirrors.cloud.tencent.com/gradle/gradle-8.13-bin.zip
        ```

---

# 2. 无法使用虚拟机运行APP

* 问题

    ```
    Android Studio的虚拟机无法正常启动，一直没办法显示虚拟机的界面。
    ```

* 解决方法

    1. 可以尝试删除原来的虚拟机，重新创建一个新的虚拟机
    2. 如果电脑内存或磁盘空间不足，可以尝试使用安卓手机来运行app。
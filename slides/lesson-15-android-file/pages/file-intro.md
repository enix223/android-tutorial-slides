# Android文件读写概述

* 在Androi中文件读写一共有以下目录:

<div style="display: flex; justify-content: space-between;">

  <div>
    <ol>
      <li style="margin-top: 24px;margin-bottom: 12px;">raw 目录</li>
      <li style="margin-bottom: 12px;">assets 目录</li>
      <li style="margin-bottom: 12px;">data/data/包名</li>
      <li style="margin-bottom: 12px;">sdcard 目录</li>
   </ol>
  </div>

  <img src="/android-file-1.png" width="500">
  
</div>

<br />
<br />

* 其中raw下的和asserts下的文件可以以资源文件的形式读取，这些目录的数据只能读取，不能写入，两者目录下的文件在打包后会原封不动的保存在apk包中，不会被编译成二进制。
  data/data/包名和sdcard目录文件可读可写。其中data/data/包名目录不需要申请权限,sdcard目录需要申请权限
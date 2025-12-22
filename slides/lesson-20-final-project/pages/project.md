# 课堂大作业

* 项目模版下载地址: [http://course.cloudesk.top/CookbookTemplate.zip](http://course.cloudesk.top/CookbookTemplate.zip)
* 实训报告模版下载: [http://course.cloudesk.top/Android实训报告模版.docx](http://course.cloudesk.top/Android实训报告模版.docx)

作业说明：

1. 基于上述模版，把代码中标记为`TODO`的地方补充完成
2. 代办事项注释中有相关的参考指引
3. 代码可参考`页面跳转作业`，`学生信息表单作业`，`列表作业`，与`数据库PPT`关于Sqlite的章节。
4. 作业代码需要跟实训报告一同上交。
5. 🚫 禁止抄袭，能做多少是多少，重要的是动手实践。

---

# 查看TODO代办事项

<img src="/public/todo.png" />

---

# 查看TODO代办事项

<img src="/public/todo-items.png" />

---

# 项目结构

- 项目中的类:

  * <span class="text-blue-700">MainActivity</span>, app首页
  * <span class="text-blue-700">EditActivity</span>, 编辑页面
  * <span class="text-blue-700">Recipe</span>, 食谱数据类
  * <span class="text-blue-700">RecipeListAdapter</span>, 食谱列表适配器，完成列表行的渲染工作
  * <span class="text-blue-700">RecipeRepo</span>, 食谱数据库操作的类，完成数据库的增删改查操作

- 页面布局

  * <span class="text-blue-700">activity_main.xml</span>, 列表页面布局
  * <span class="text-blue-700">activity_edit.xml</span>, 编辑页面布局
  * <span class="text-blue-700">item_recipe.xml</span>, 列表单元格布局

---

# 项目包含的知识点

* 基础组件的使用，包括TextView, EditText, SeekBar, ImageView
* 容器组件的使用，包括LinearLayout, ListView
* Activity的使用，包括生命周期函数、页面间的跳转
* 数据库的使用，sqlite实现增删改查
* 图片资源的使用

---

# 作业提交

需要提交作业的同学，可以以邮件的形式，附件包含源代码+实训报告（zip压缩），发送至`sdptandroid@163.com`

注意事项:

1. 标题请以如下形式:

```
大作业 23010111 张三
```

收到邮件后，系统会自动发送答复邮件，若未收到自动答复邮件，需检查邮件标题是否已按格式填写，或稍后再尝试发送

2. 发送的附件尽量不要超过50兆，项目文件夹内的两个子目录`build`, `app/build`可以先删除后再压缩，这样可以减少压缩包的大小。
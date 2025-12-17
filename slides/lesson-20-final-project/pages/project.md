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

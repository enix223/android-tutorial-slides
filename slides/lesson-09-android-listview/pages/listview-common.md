# 如何使用ListView 
以下将以建立一个菜谱列表的界面为例说明ListView的使用

<img src="/listview-showcase.jpg" class="flex w-[13vw]" />

---

# 数据建模
分析食谱需要用到的属性

<div class="flex flex-row justify-center gap-2">
    <img src="/homework-code-0.png" class="flex w-[40vw] border border-solid" />
    <div class="flex flex-1">
        <ul>
            <li>菜谱的唯一ID: <span class="text-blue-600">id</span>, 类型使用整型int</li>
            <li>菜谱的名称: <span class="text-blue-600">title</span>, 类型使用字符串String</li>
            <li>菜谱的描述: <span class="text-blue-600">desc</span>, 类型使用字符串String</li>
            <li>菜谱图片: <span class="text-blue-600">image</span>, 类型使用整型int，需要添加@DrawableRes注解</li>
        </ul>
    </div>
</div>

---

# 创建一个菜谱列表Activity

<img src="/homework-code-2.png" class="flex w-[50vw] border border-solid" />

---

# 菜谱列表布局

<img src="/homework-code-3.png" class="flex w-[50vw] border border-solid" />

---

# 菜谱元素行布局

<img src="/homework-code-4.png" class="flex w-[29vw] border border-solid" />

---

# 菜谱列表适配器Adapter

<img src="/homework-code-1.png" class="flex h-[60vh] border border-solid" />

---

# 课堂练习
完成以上菜谱列表页面的开发

| 菜谱名称 | 描述 | 图片资源 |
|-|-|-|
| 水果沙拉 | 水果沙拉有助于减肥 | R.drawable.salad |
| 番茄炒蛋 | 家常下饭菜，营养又简单 | R.drawable.meat |
| 麻婆豆腐 | 麻辣鲜香，米饭最佳搭档 | R.drawable.salad |
| 红烧排骨 | 浓油赤酱，骨酥肉烂入味 | R.drawable.meat |
| 拍黄瓜 | 清爽脆嫩，夏日开胃小凉菜 | R.drawable.meat |
| 清蒸鲈鱼 | 肉质鲜嫩，原汁原味健康菜 | R.drawable.salad |
| 油焖大虾 | 咸鲜红亮，壳酥肉嫩味醇 | R.drawable.meat |
| 地三鲜 | 东北家常味，土豆茄子椒香 | R.drawable.salad |

图片资源
- http://course.cloudesk.top/android-tutorial-slides/lesson-09/salad.jpg
- http://course.cloudesk.top/android-tutorial-slides/lesson-09/meat.jpg

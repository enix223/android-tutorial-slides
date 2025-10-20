
# 什么是 ListView

- ListView 是 Android 中最常用的**列表控件**之一，用于显示**可滚动的数据集合**。
- 它继承自 `AdapterView`，通过 Adapter 将数据绑定到列表项。
- 每一行列表项可以是简单文本，也可以是复杂布局（如图片+文字）。
- 优点：
  - 易于快速实现列表显示
  - 支持点击事件
  - 支持分隔线和选中效果
- 缺点：
  - 对大数据量性能不如 `RecyclerView`
  - 定制复杂布局需要自定义 Adapter

---


# ListView 常用属性

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">属性</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">说明</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">示例值</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:id</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">reference</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">控件 ID</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">@+id/listView</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:layout_width</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">dimension</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">宽度</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">match_parent</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:layout_height</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">dimension</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">高度</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">wrap_content</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:divider</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawable</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">分隔线</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">@android:color/darker_gray</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:dividerHeight</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">dimension</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">分隔线高度</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">1dp</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:choiceMode</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">enum</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">选择模式</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">none / singleChoice / multipleChoice / multipleChoiceModal</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:listSelector</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawable</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">选中效果</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">@android:color/holo_blue_light</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">android:scrollbars</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">enum</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">滚动条样式</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">horizontal / vertical / none</div>
  </div>

</div>

---


# 为什么要自定义适配器？

- 原因在于，当我们想用一些其他的展现方式。

1. 我们定义一个自定义适配器 MyAdapter继承ArrayAdapter。

2. 自定义适配器中常用的方法：getCount、getView、getItem、getItemId。



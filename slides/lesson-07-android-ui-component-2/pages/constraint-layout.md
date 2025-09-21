
## ConstraintLayout

- ConstraintLayout 是 Android 提供的一个强大的布局，用于在一个层级内构建复杂的界面。与传统的多层布局（如 LinearLayout、RelativeLayout）相比，ConstraintLayout 能够减少布局层级并提供更灵活的布局方式

- 每个子视图在 ConstraintLayout 中至少需要 两个约束（如：水平和垂直），以便确定它的位置。常用的约束属性有：

  app:layout_constraintStart_toStartOf="@+id/..."

  app:layout_constraintEnd_toEndOf="@+id/..."
  
  app:layout_constraintTop_toTopOf="@+id/..."
  
  app:layout_constraintBottom_toBottomOf="@+id/..."
  
  app:layout_constraintHorizontal_bias="0.5"
  
  app:layout_constraintVertical_bias="0.5"

--- 

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-1.png"/>
</div>

--- 

- Chains链对父控件的剩余空间有三种分配方式，即Spread、spread inside、packed，默认值是Spread即将控件包括第一个控件和最后一个两边均匀分配

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-chains.png"/>
</div>

---

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-2.gif"/>
</div>

---

## 百分比布局
- ConstraintLayout 还支持控件的尺寸按照父布局尺寸的百分比来设置

  app:layout_constraintWidth_percent 宽度占父布局百分比比例
 
  app:layout_constraintHeight_percent 高度占父布局百分比比例
  
<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-3.png" width="600"/>
</div>

---

## 圆形定位

- ConstraintLayout 还支持圆型定位，将一个控件的中心以一定的角度和距离约束到另一个控件的中心上，相当于在一个圆上放置一个控件。

- 圆形定位主要通过以下三个属性值来控制

  layout_constraintCircle：引用另一个控件的 id。

  layout_constraintCircleRadius：到另一个控件中心的距离。

  layout_constraintCircleAngle：控件的角度（顺时针，0 - 360 度）。

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-4.png"/>
</div>

---

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-5.png"/>
</div>

---

## Guideline

- Guideline是ConstraintLayout布局里面的一个工具类，其作用相当于一条辅助线，默认不可见，可以用于辅助布局
  
  layout_constraintGuide_percent ：按照比例设置辅助线的位置
  
  layout_constraintGuide_begin：按照值设置辅助线的位置

- Guideline辅助线的位置既可以按照百分比来设置，也可以按照值来设置。

---

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-6.gif"/>
</div>

---

## Group 

- 可以对一组的控件同时设置其可见性的值Visible、Invisible或者Gone。
- 其使用方法如下：在布局中右键添加Group,再将一组控件拖动到Group中，通过控制Group的属性来调节一组控件的可见性。

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-7.gif"/>
</div>

---

## Barrier

- Barrier和Guideline 一样,本身不可见，用户辅助布局。

- 假如有个这样的需求，页面中有个两个Button1和Button2，一个TextView3 ，现在需要让TextView3 处于Button1或Button2的右边，对齐Button1和Button2中较宽的控件。

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-8.gif"/>
</div>

---

- 将Barrier的barrierDerection属性设置成right,right属性表示其他控件对齐barrier控件中的靠右的控件

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-9.gif"/>
</div>

---

- 将textview3左边的约束加到Barrier上

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-10.gif"/>
</div>

---

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-11.png" width="580"/>
</div>

---

## 测试效果如下

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-12.gif"/>
</div>
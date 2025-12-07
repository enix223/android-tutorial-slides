# 动画的本质与作用
动画的本质是通过快速连续展示静态画面（帧）来形成动态视觉效果的技术。在移动应用中，精心设计的动画能够：

* 提升用户体验：使交互流程更自然流畅。例如，澎湃OS2的连续打断动画提供了卓越的流畅感。

* 引导用户注意力：通过动效将用户的视线引向关键信息或操作区域。例如，页面切换时的焦点引导，或QQ消息图标上的小红点提示。

* 增强操作反馈：即时、可视化的反馈让用户确认操作已被接收。例如，按钮的点击态、列表项被按下的效果，以及加载成功/失败的提示动画。

* 提升产品质感：统一的、富有细节的动效能够强化品牌形象，使应用显得更加精致和专业。


<div class="flex flex-col items-center justify-center">
    <img src="/android-animation-1.gif" width="200"/>
</div>

---

# Android动画体系演进


<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">动画类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">API Level</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">特点</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">局限性</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">帧动画</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">1+</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">逐帧播放一系列图片，实现原理简单直观，类似GIF</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">内存占用高（每帧都是一张图），动画效果固定不灵活</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">补间动画</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">1+</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">对View进行平移、缩放、旋转、透明度四种视觉变换</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">仅修改视图的绘制位置，不改变其实际布局属性，可能导致点击区域不跟随动画移动</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">属性动画</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">11+(3.0)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">真正改变对象的属性值，可对任何对象的任何属性（如自定义View的进度）做动画，功能强大且灵活</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">在API 11以下设备上需要使用兼容库（如NineOldAndroids）</div>
  </div>
</div>

---

# Android动画的分类​

基于上述演进历程，Android平台上的动画实现主要分为以下四类：

* 帧动画

  实现：AnimationDrawable。

  描述：通过按顺序播放一组预定义的位图资源来创建动画。适用于简单的、序列帧式的效果。

* 补间动画 (视图动画)

  实现：TranslateAnimation、ScaleAnimation、RotateAnimation、AlphaAnimation。

  描述：指定动画开始和结束时视图的视觉状态（位置、大小、角度、透明度），系统自动计算中间帧。注意：它只影响绘制，不改变视图的真实属性。

* 属性动画

  实现：ValueAnimator、ObjectAnimator、ViewPropertyAnimator。

  描述：当前最核心、最推荐的动画系统。它在动画周期内，通过TypeEvaluator和TimeInterpolator动态计算并更新目标对象的真实属性值，从而实现动画。功能极其强大，几乎可以实现任何动画效果。

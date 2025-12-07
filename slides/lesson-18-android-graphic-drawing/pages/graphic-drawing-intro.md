# Canvas 类简介
  Canvas（画布） 是 Android 提供的一个用于绘制的类。

* 可以理解为一张“虚拟画布”，在上面可以绘制各种图形、文本或图片。

* Canvas 本身不存储像素，它需要 Bitmap 来承载绘制内容。

* Canvas 的获取方式：

1. 系统传入的 Canvas

    在自定义 View 中，通过重写 onDraw(Canvas canvas) 获取。

    你在 onDraw 中对 canvas 的操作会直接显示在 View 上。

    对于 ViewGroup，可以通过 dispatchDraw(Canvas canvas) 将 Canvas 传递给子 View，让子 View 在同一画布上绘制。

2. 自己创建 Canvas

    通过 new Canvas() 创建，可以指定一个 Bitmap 作为绘制目标。

    这种方式常用于离屏绘制（off-screen drawing），比如生成图片或缓存绘制结果。

---

# 绘制所需的基本组件

* Bitmap

    用于存放像素，Canvas 的绘制内容会写入 Bitmap。

* Canvas

    承载绘制调用，像一个画布。

* 图元（Primitives）

    矩形 Rect

    路径 Path

    文本 Text
    
    位图 Bitmap

* Paint（画笔）

    描述绘制的颜色、样式、宽度等属性。

---

# Paint（画笔）常用方法

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">方法</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">作用</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setColor()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置颜色</div>
  </div>
  
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setAntiAlias()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置抗锯齿效果</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setARGB()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置 A/R/G/B 值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setAlpha()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置透明度</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setTextSize()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置文本大小</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setStyle()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置绘制风格（填充/描边）</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setStrokeWidth()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置描边宽度</div>
  </div>
</div>

---

# Canvas 常用 API

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">方法</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">作用</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawLine(float startX, float startY, float stopX, float stopY, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制直线</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawRect(Rect/float left, top, right, bottom, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制矩形</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawCircle(float cx, float cy, float radius, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制圆形</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawOval(RectF rect, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制椭圆</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制弧形</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawRoundRect(RectF rect, float rx, float ry, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制圆角矩形</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawPath(Path path, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制路径</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawText(String text, float x, float y, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制文本</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawBitmap(Bitmap bitmap, float left, float top, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制位图</div>
  </div>

</div>

---

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">方法</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">作用</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawPoint(float x, float y, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制单个点</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawPoints(float[] pts, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制多个点</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">drawLines(float[] pts, Paint paint)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">绘制多条线段</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">translate(float dx, float dy)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">平移画布</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">rotate(float degrees)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">旋转画布</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">scale(float sx, float sy)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">缩放画布</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">skew(float sx, float sy)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">倾斜画布</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">save()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">保存当前画布状态</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">restore()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">恢复上一次保存的画布状态</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">saveLayer(...)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">保存图层（支持透明度和混合模式）</div>
  </div>

</div>

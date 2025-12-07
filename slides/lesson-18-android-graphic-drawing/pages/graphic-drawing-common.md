# 绘制位图

```java
//  bitmap 绘制在画布上，同时指定位图左上角相对于画布的坐标，大小与原位置相同，不进行任何缩放。
public void drawBitmap( Bitmap bitmap, float left, float top,  Paint paint)
//  下面两个方法从源bitmap中抠出一块大小区域为src的图片并绘制到canvas的dst处。src和ds 的大小与比例关系影响到最终的绘制效果,这个过程是自动缩放以适应dest区域的
public void drawBitmap(Bitmap bitmap,  Rect src, RectF dst, Paint paint)
public void drawBitmap(Bitmap bitmap,  Rect src, Rect dst, Paint paint)
```

* ps:绘制位图时，除非需要进行位图运算，否则，并不需要指定 paint 对象，直接传递null 即可。

案例-使用drawBitmap方法在ImageView上绘制2个位图,一个按原始大小绘制,另一个则按2倍大小绘制


```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView iv = findViewById(R.id.iv);
    // 创建空白位图
    Bitmap bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
    // 创建画布并关联位图
    Canvas canvas = new Canvas(bitmap);
    // 绘制背景色,便以区分
    canvas.drawColor(Color.BLACK);
    // 获取资源目录下的原图
    Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
```

---

```java
    // 绘制原图
    canvas.drawBitmap(sourceBitmap, 0, 0, null);
    // 获取原图宽高
    int bmpWidth = sourceBitmap.getWidth();
    int bmpHeight = sourceBitmap.getHeight();
    // 创建原图的矩形
    Rect src = new Rect(0, 0, bmpWidth, bmpHeight);
    // 创建放大后的矩形,位于原图下方
    Rect dest = new Rect(0, bmpHeight, bmpWidth * 2, bmpHeight + bmpHeight * 2);
    // 绘制放大后的位图
    canvas.drawBitmap(sourceBitmap, src, dest, null);

    // 显示到ImageView上
    iv.setImageBitmap(bitmap);
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-1.png" />
</div>

---

# 绘制点

* 点的大小取决于 setStrokeWidth()方法的参数，参数值越大，点也就越大。所以，不要以为一个点就是屏幕上的一个像素。如果将 stroke 的宽度设置为足够大，我们发现最终绘制出来的点其实是一个正方形。绘制点的方法一共有三个：

```java
// 该方法在（x，y）处绘制一个点。
public void drawPoint(float x, float y, Paint paint)
// 该方法的参数 pts 是一个数组，从下标 0 开始每 2 个数确定一个点，连续绘制多个点。多余的元素会忽略。
public void drawPoints(float[] pts, Paint paint)
// 从 pts 数组中的第 offset 处开始取出 count 个数字，以 2 个数为一组确实一个点，连 续绘制若干个点。忽略多余的元素
public void drawPoints(float[] pts, int offset, int count,	Paint paint)
```

  案例：

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView iv = findViewById(R.id.iv);
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    // 绘制背景
    canvas.drawColor(Color.BLACK);
```

---

```java
    //画一个红色的点
    Paint paint = new Paint();
    paint.setColor(Color.RED);
    paint.setStrokeWidth(10);
    canvas.drawPoint(120, 20, paint);

    // 画一组蓝色的点
    paint.setColor(Color.BLUE);
    float[] points = new float[]{10, 10, 50, 50, 50, 100, 50, 150};
    canvas.drawPoints(points, paint);

    // 画一组绿色的点
    paint.setColor(Color.GREEN);
    points = new float[]{20, 20, 60, 60, 60, 80, 60, 180};
    canvas.drawPoints(points, 3, 4, paint);

    // 显示在ImageView上
    iv.setImageBitmap(bitmap);
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-2.png"/>
</div>

---

# 绘制直线
  两个点确定一条直线，所以，绘制线条时，需要指定两个点的坐标。同画点一样，绘制线条也有 3 个重载的方法：

```java
// 在（startX,startY）和（stopX,stopY）两个点之间绘制一条直线。
public void drawLine(float	startX,	float startY, float stopX, float stopY,	Paint paint)
// pts 数组中每4个数一组绘制一条直线，多余的元素会忽略。	
public void drawLines(float[] pts,	Paint paint)	
// 从 pts 数组中的 offset 索引处开始，取出 count 个元素，并以 4 个数一组绘制直线，忽略多余的元素。
public void drawLines(float[] pts,	int offset,int count, Paint paint)
```

案例：
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView iv = findViewById(R.id.iv);
    // 创建空白位图
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    // 绘制背景
    canvas.drawColor(Color.BLACK);

    Paint paint = new Paint();
    paint.setStrokeWidth(5); // 设置线宽
```

---

```java
    // 绘制单条直线
    paint.setColor(Color.RED);
    canvas.drawLine(50, 50, 200, 50, paint); // 从 (50,50) 到 (200,50)

    // 绘制多条直线（用 pts 数组，每4个数为一条线）
    paint.setColor(Color.BLUE);
    float[] lines = new float[]{
        50, 100, 200, 100,   // 第一条线
        50, 120, 200, 150,   // 第二条线
        50, 180, 200, 180    // 第三条线
    };
    canvas.drawLines(lines, paint);

    // 绘制部分线段（从 offset 开始，取 count 个元素）
    paint.setColor(Color.GREEN);
    float[] moreLines = new float[]{
        50, 220, 200, 220,   // 第一条线
        50, 250, 200, 300,   // 第二条线
        50, 320, 200, 320    // 第三条线
    };
    int offset = 4; // 从第二条线开始
    int count = 4;  // 只绘制第二条线
    canvas.drawLines(moreLines, offset, count, paint);

    // 显示到 ImageView
    iv.setImageBitmap(bitmap);
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-3.png"  width="300"/>
</div>

---

# 绘制矩形
  绘制矩形时，参数分为两种：一种是指定 left、top、right、bottom 等 4 个参数，另一种直接指定一个 Rect 对象或 RectF 对象。绘制直角矩形的三个重载方法如下：

```java
public void drawRect(float left,float top,	float right,float bottom,Paint paint)

public void drawRect(Rect r,Paint	paint)

public void drawRect(RectF	r,Paint	paint)
```

  圆角矩形的几何形状比直角矩形相对复杂一些，我们需要指定 4 个拐角的弧度，4 个角的弧度不能单独设置，而是统一设置为相同的值。拐角弧度实际上是圆或椭圆的一段弧线
  绘制圆角矩形一共有 2 个重载的方法：

```java
// 该方法用于绘制一个圆角矩形，left、top、right、bottom 构建一个矩形，rx、ry 分别是圆角处的水平半径和垂直半径。rx 和 ry 不一定相同，如果不同，则是椭圆上的一段弧线。
public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry,Paint paint)
// 该方法是上面方法的另一种重载形式。
public void drawRoundRect(RectF rect,float	rx,float ry,Paint paint)
```

案例：
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
```

---

```java
    ImageView iv = findViewById(R.id.iv);
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.BLACK);

    Paint paint = new Paint();
    paint.setAntiAlias(true);
    // 该方法用于设置落笔时的样式，控制我们的画笔在离开画板时留下的最后一点图形
    paint.setStrokeCap(Paint.Cap.ROUND);
    // 当绘图样式为 STROKE 时，该方法用于指定线条连接处的拐角样式，能使绘制的图形更加平滑
    paint.setStrokeJoin(Paint.Join.ROUND);
    paint.setColor(Color.RED);
    // 绘制红色的直角矩形
    canvas.drawRect(new Rect(0, 0, 100, 100), paint);

    // 绘制绿色的圆角矩形
    paint.setColor(Color.GREEN);
    canvas.drawRoundRect(new RectF(100, 100, 200, 200), 20, 20, paint);

    // 绘制蓝色的圆角矩形(空心)
    paint.setColor(Color.BLUE);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(5);
    // 圆角的半径可以不一样
    canvas.drawRoundRect(new RectF(200, 200, 400, 400), 10, 20, paint);

    iv.setImageBitmap(bitmap);
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-4.png"  width="300"/>
</div>

---

# 绘制圆

* 在对图形进行分类时，我将圆、椭圆、扇形、弧线统一归类到“圆”这一类中，扇形和弧线可以认为是圆或椭圆的一部分,椭圆的大小是由他的外切矩形来决定的，这实际上和几何学中的定义完全一致，绘制椭圆的方法如下: 

```java
public void drawOval(float	left, float top, float right, float bottom, Paint paint)

public void drawOval(RectF	oval, Paint paint)
```

* 绘制椭圆时，如果外切矩形的长和宽相等，即为正方形，绘制出来的图形就是一个正圆，但 是 Cavnas 类供了另一个更加简单实用的方法，供圆点的坐标和半径即可。

```java
public void drawCircle(float cx, float cy,	float radius, Paint paint) //（cx、cy）为圆心坐标，radius 为圆的半径
```

* 绘制弧线和扇形的方法如下:

```java
// 参数oval是规定椭圆的范围, startAngle 表示起始角度，sweepAngle 表示扇形或弧线所占的角度，
// 正数表示顺时针，负数表示逆时针,useCenter 参数询问是否要使用中心点，为true 表示扇形，为 false 表示弧线
public void drawArc(RectF oval,float startAngle,float sweepAngle,boolean useCenter,Paint paint)

public void drawArc(float left, float top, float right, float bottom, 
					float startAngle, float sweepAngle,boolean useCenter,Paint paint)
```

下面的代码演示了弧线和扇形的绘制方法，采用了 Style.STROKE 的图形模式，如果将 Style设置为 Style.FILL，不管是弧线还是扇形，都可以使用颜色进行填充

---

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main6);
    ImageView iv = findViewById(R.id.iv);
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.BLACK);

    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStrokeWidth(5);
    paint.setStrokeJoin(Paint.Join.ROUND);
    paint.setStrokeCap(Paint.Cap.ROUND);

    // 空心模式
    paint.setStyle(Paint.Style.STROKE);

    // 绘制正圆
    paint.setColor(Color.BLUE);
    canvas.drawCircle(250, 400, 50, paint); // cx=250, cy=400, 半径=50
    RectF oval = new RectF(100, 100, 400, 200);

    // 绘制椭圆
    paint.setColor(Color.GRAY);
    canvas.drawOval(oval, paint);

```

---

```java
    // 绘制圆弧,起始角度90度(也就是y轴原点),扫过45度(也就是8点中方向)
    paint.setColor(Color.RED);
    canvas.drawArc(oval,90,45,false,paint);

    // 绘制扇形,起始角度是0度(也就是在x轴原点),扫过-45度(也就是3点钟方向,因为Android坐标系是向下的)
    paint.setColor(Color.GREEN);
    canvas.drawArc(oval,0,-45,true,paint);
    iv.setImageBitmap(bitmap);
}
```

<br />

<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-5.png"  width="300"/>
</div>

---

# 绘制路径

Path 是 Graphics2D 中一个非常重要的概念，表示“路径”，理解该概念时保持“路径”的本色就好。路径可以是直的、也可以是弯的，可以是闭合的、也可以是非闭合的，可以是圆形的、也可以是方形的，可以是单个的、也可以是多个的，可以是简单的、也可以是复杂的……总的来说，路径是基于普通图形但是功能比普通图形更强的一种复杂图形。

* Path 是一个类，用于绘制复杂图形，创建之初什么也没有，只有往 Path 中添加了具体的形状，Path 才会清晰可见。绘制 Path 时，所有信息都存储在 Path 对象中，Canvas 根据 Path 对象来绘制相应的图形。

* 往 Path 中添加线条
  
  通过 Path 可以绘制出奇形怪状的线条，并能将线条组合在一起变成折线，闭合后就是一个多边形了。这就是 Path 的厉害之处。为此，Path 类中定义了 5 个方法：

```java
// 将画笔移动到点（x，y）的位置，使用的是绝对定位
public void moveTo(float x,float y)
// 将画笔移动到一个新点，新点在上一个点的基础上偏移（dx，dy），也就是说，新点的坐标为（x+dx，y+dy）。这里使用的是相对定位。首字母“r”就是“relative（相对）”的意思
public void rMoveTo(float dx,float	dy)
// 将画笔连接到点（x，y）的位置，并在上一个点与当前点之前画一条直线。使用的是绝对定位。
public void lineTo(float x,float y)
// 将画笔移动到一个新点，新点在上一个点的基础上偏移（dx，dy），新点的坐标为（x+dx，y+dy），同时，在新点与上一个点之间画一条直线。这里使用的是相对定位。
public void rLineTo(float dx,float dy)
// 在第一个点和最后一个点之前画一条直线，形成闭合区域。
public void close()
```
---

下面的案例使用 Path 绘制了一个五角星，这不是一个完美的五角星几何图形，因为五个点的坐标并没有正确计算出来，只是算了个大概。首先调用了 moveTo(0, 150)定义好这次绘图的起 点，接下来调用 rLineTo()方法通过相对定位计算出下一个点的坐标，并使用直线连接，最后，调用 close()方法连接最后一点和第一个点以形成闭合区域。

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main7);
    ImageView iv = findViewById(R.id.iv);
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.BLACK);

    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(Color.WHITE);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(5);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeJoin(Paint.Join.ROUND);

    // 创建路径
    Path path = new Path();
    path.moveTo(0, 150);
    path.rLineTo(300, 0);
    path.rLineTo(-300, 150);
    path.rLineTo(150, -300);
    path.rLineTo(150, 300);
```

---

```java
    path.close();// 连接最后一点和第一个点以形成闭合区域

    // 绘制路径
    canvas.drawPath(path, paint);

    iv.setImageBitmap(bitmap);
}
```

<br />

<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-6.png"  width="300"/>
</div>

---

# 绘制文字

* Canvas为我们供了两组方法，一组直接从指定的位置开始绘制文字，另一组沿着 Path 绘制文字：

```java
public void drawText(char[] text, int index, int count, float x, float y, Paint paint)
public void drawText(String text, float x, float y, Paint paint)
public void drawText(String text, int start, int end, float x, float y, Paint paint)
public void drawText(CharSequence text, int start, int end, float x, float y, Paint paint)
```

* 上面这一组方法是从指定的位置（坐标）开始绘制文字，虽然都是字符串，但是供了三种形式：char[]、String 和 CharSequence，本质上并没有什么不同，参数 index 和count、start 和 end 可以从字符串中取出子串，而参数 x、y 就是文字绘制的坐标位置，其中 y 是文字的 baseline 的值

```java
public void drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint)
public void drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
```

* 上面这两个重载的 drawTextOnPath()方法用于沿着 Path 定义好的路径绘制文字，这是一个很在趣的功能，文字在 Path 的带领下龙飞凤舞，灵活多变。参数 hOffset 和 vOffset 用于定义文字离 Path 的水平偏移量和垂直偏移量，正数和负数影响文字与路径的相对位 置。同样的，也支持绘制从字符数组中截取的子串，index 表示起始索引，count 表示要截取的长度。

* 下面的案例中绘制了 4 个字符串，一个绘制所有的字符串，中间两个截取子串进行绘制，最后一个沿着 Path 绘制出所有的文字，为了更好的理解文字与路径的关系，所以把对应的路径也绘制出来了

---

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main12);

    ImageView iv = findViewById(R.id.iv);
    Bitmap bitmap = Bitmap.createBitmap(800, 450, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.BLACK);
    Paint paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setStyle(Paint.Style.FILL);
    paint.setAntiAlias(true);
    paint.setTextSize(30);
    String text = "Hello Canvas! 绘制文字示例";

    // 1.直接绘制
    canvas.drawText(text, 10, 50, paint);
    // 2.截取数量来绘制
    paint.setColor(Color.RED);
    // start从6开始,end=11,表示取[6,11)范围的数量
    canvas.drawText(text, 6, 11, 10, 100, paint);
    // 截取方式二
    paint.setColor(Color.BLUE);
    // index从12开始,count=5,表示从索引12开始取5个字符
    canvas.drawText(text.toCharArray(), 12, 5, 10, 150, paint);

```

---

```java

    // 3.通过路径绘制
    // 先创建路径
    Path path = new Path();
    path.moveTo(10, 300);
    path.quadTo(100, 100, 700, 400);
    paint.setColor(Color.GREEN);
    // 将文字绘制到路径上
    canvas.drawTextOnPath(text, path, 10, 30, paint);
    // 绘制路径,方便查看
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(2);
    paint.setColor(Color.RED);
    canvas.drawPath(path, paint);
    // 绘制到ImageView上
    iv.setImageBitmap(bitmap);
}
```


<div class="flex flex-col items-center justify-center">
    <img src="/android-graphic-drawing-7.png"  width="300"/>
</div>
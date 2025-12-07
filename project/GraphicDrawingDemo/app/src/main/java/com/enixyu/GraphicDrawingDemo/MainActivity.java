package com.enixyu.GraphicDrawingDemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private ImageView iv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    iv = findViewById(R.id.iv);

    setupButtonListeners();
    drawLine();
  }

  private void setupButtonListeners() {
    findViewById(R.id.btn_bitmap).setOnClickListener(v -> {
      drawBitmap();
    });

    findViewById(R.id.btn_point).setOnClickListener(v -> {
      drawPoint();
    });

    findViewById(R.id.btn_line).setOnClickListener(v -> {
      drawLine();
    });

    findViewById(R.id.btn_rect).setOnClickListener(v -> {
      drawRect();
    });

    findViewById(R.id.btn_circle).setOnClickListener(v -> {
      drawCircle();
    });

    findViewById(R.id.btn_path).setOnClickListener(v -> {
      drawPath();
    });

    findViewById(R.id.btn_path2).setOnClickListener(v -> {
      drawPath2();
    });

    findViewById(R.id.btn_text).setOnClickListener(v -> {
      drawText();
    });
  }

  private void drawBitmap() {
    // 创建空白位图
    Bitmap bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
    // 创建画布并关联位图
    Canvas canvas = new Canvas(bitmap);
    // 绘制背景色,便以区分
    canvas.drawColor(Color.BLACK);
    // 获取资源目录下的原图
    Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(),
        R.drawable.ic_launcher);
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

  private void drawPoint() {
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    // 绘制背景
    canvas.drawColor(Color.BLACK);

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

  private void drawLine() {
    // 创建空白位图
    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    // 绘制背景
    canvas.drawColor(Color.BLACK);

    Paint paint = new Paint();
    paint.setStrokeWidth(5); // 设置线宽

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

  private void drawRect() {
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

  private void drawCircle() {
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

    paint.setColor(Color.BLUE);
    canvas.drawCircle(250, 400, 50, paint);

    RectF oval = new RectF(100, 100, 400, 200);
    // 绘制椭圆
    paint.setColor(Color.GRAY);
    canvas.drawOval(oval, paint);

    // 绘制圆弧,起始角度90度(也就是y轴原点),扫过45度(也就是8点中方向)
    paint.setColor(Color.RED);
    canvas.drawArc(oval,90,45,false,paint);

    // 绘制扇形,起始角度是0度(也就是在x轴原点),扫过-45度(也就是3点钟方向,因为Android坐标系是向下的)
    paint.setColor(Color.GREEN);
    canvas.drawArc(oval,0,-45,true,paint);

    iv.setImageBitmap(bitmap);
  }

  private void drawPath() {
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

    path.close();// 连接最后一点和第一个点以形成闭合区域

    // 绘制路径
    canvas.drawPath(path, paint);

    iv.setImageBitmap(bitmap);
  }

  private void drawPath2() {

    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.BLACK);

    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(Color.YELLOW);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(5);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeJoin(Paint.Join.ROUND);

    float cx = 250;  // 中心点x
    float cy = 250;  // 中心点y
    float R = 100;   // 外接圆半径

    // 计算五角星五个顶点
    float[] points = new float[10]; // x0,y0,x1,y1,...x4,y4
    for (int i = 0; i < 5; i++) {
      double angle = Math.toRadians(-90 + i * 72); // 从竖直向上开始
      points[i*2] = (float)(cx + R * Math.cos(angle));
      points[i*2 + 1] = (float)(cy + R * Math.sin(angle));
    }

    // 按五角星连接顺序绘制
    Path path = new Path();
    path.moveTo(points[0], points[1]);
    path.lineTo(points[4], points[5]);
    path.lineTo(points[8], points[9]);
    path.lineTo(points[2], points[3]);
    path.lineTo(points[6], points[7]);
    path.close();

    canvas.drawPath(path, paint);
    iv.setImageBitmap(bitmap);
  }

  private void drawText() {
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
}

package com.enixyu.mqttcontroller.ui;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;

/**
 * @noinspection unused
 */
public class ColorRingView extends View {

  private Paint colorRingPaint;
  private Paint selectorPaint;
  private float centerX, centerY;
  private float radius;
  private final float selectorRadius = 20f;
  private float currentAngle = 0f;
  private float saturation = 1.0f;
  private OnColorChangedListener colorListener;

  public interface OnColorChangedListener {

    void onColorChanged(Color color);

    void onColorSelected(Color color);
  }

  public ColorRingView(Context context) {
    super(context);
    init();
  }

  public ColorRingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    colorRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    colorRingPaint.setStyle(Paint.Style.STROKE);
    colorRingPaint.setStrokeWidth(50f);
    colorRingPaint.setStrokeCap(Paint.Cap.ROUND);

    selectorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    selectorPaint.setStyle(Paint.Style.STROKE);
    selectorPaint.setStrokeWidth(3f);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    centerX = w / 2f;
    centerY = h / 2f;
    radius = Math.min(w, h) / 2f - selectorRadius - 10f;

    // Create color ring shader
    int[] colors = {
        Color.RED,           // 0°
        Color.MAGENTA,       // 60°
        Color.BLUE,          // 120°
        Color.CYAN,          // 180°
        Color.GREEN,         // 240°
        Color.YELLOW,        // 300°
        Color.RED            // 360°
    };

    float[] positions = {0f, 1f / 6f, 2f / 6f, 3f / 6f, 4f / 6f, 5f / 6f, 1f};
    Shader colorRingShader = new SweepGradient(centerX, centerY, colors, positions);
    colorRingPaint.setShader(colorRingShader);
  }

  @Override
  protected void onDraw(@NonNull Canvas canvas) {
    super.onDraw(canvas);

    // Draw the color ring
    canvas.drawCircle(centerX, centerY, radius, colorRingPaint);

    // Draw selector
    drawSelector(canvas);
  }

  private void drawSelector(Canvas canvas) {
    // Calculate selector position using the same logic as touch detection
    float angleRadians = (float) Math.toRadians(currentAngle);
    float selectorX = centerX + (float) (radius * Math.cos(angleRadians));
    float selectorY = centerY + (float) (radius * Math.sin(angleRadians));

    // Draw outer black ring
    selectorPaint.setColor(Color.BLACK);
    canvas.drawCircle(selectorX, selectorY, selectorRadius, selectorPaint);

    // Draw inner white ring
    selectorPaint.setColor(Color.WHITE);
    canvas.drawCircle(selectorX, selectorY, selectorRadius - 2, selectorPaint);

    // Draw current color inside selector
    Paint fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    fillPaint.setStyle(Paint.Style.FILL);
    fillPaint.setColor(getCurrentColor().toArgb());
    canvas.drawCircle(selectorX, selectorY, selectorRadius - 4, fillPaint);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_MOVE:
        updateAngleFromTouch(x, y);
        if (colorListener != null) {
          colorListener.onColorChanged(getCurrentColor());
        }
        invalidate();
        return true;

      case MotionEvent.ACTION_UP:
        if (colorListener != null) {
          colorListener.onColorSelected(getCurrentColor());
        }
        return true;
    }

    return super.onTouchEvent(event);
  }

  private void updateAngleFromTouch(float x, float y) {
    // Calculate vector from center to touch point
    float dx = x - centerX;
    float dy = y - centerY;

    // Calculate angle in radians using atan2
    // atan2 returns angle between -PI and PI radians
    double angleRadians = Math.atan2(dy, dx);

    // Convert to degrees (0-360 range)
    currentAngle = (float) Math.toDegrees(angleRadians);

    // Normalize to 0-360 range
    if (currentAngle < 0) {
      currentAngle += 360;
    }
  }

  public Color getCurrentColor() {
    return angleToColor(currentAngle, saturation);
  }

  private Color angleToColor(float angle, float saturation) {
    // Convert angle to HSV (Hue should be in 0-360 range)
    float[] hsv = {angle, saturation, 1.0f};
    int color = Color.HSVToColor(hsv);
    int red = Color.red(color);
    int green = Color.green(color);
    int blue = Color.blue(color);
    int alpha = Color.alpha(color);
    return Color.valueOf(Color.argb(alpha, red, blue, green));
  }

  /**
   * Set color using HSV values
   *
   * @param h Hue (0-360)
   * @param s Saturation (0-1)
   * @param v Value/Brightness (0-1)
   */
  public void setColorHSV(float h, float s, float v) {
    this.currentAngle = h % 360;
    this.saturation = Math.max(0f, Math.min(1f, s));
    invalidate();
    if (colorListener != null) {
      colorListener.onColorChanged(getCurrentColor());
    }
  }

  /**
   * Set color using RGB values
   *
   * @param r Red (0-255)
   * @param g Green (0-255)
   * @param b Blue (0-255)
   */
  public void setColorRGB(int r, int g, int b) {
    float[] hsv = new float[3];
    Color.RGBToHSV(r, g, b, hsv);
    setColorHSV(hsv[0], hsv[1], hsv[2]);
  }

  /**
   * Set color using Android Color value
   *
   * @param color Android Color value
   */
  public void setColor(Color color) {
    setColorRGB(Color.red(color.toArgb()), Color.green(color.toArgb()), Color.blue(color.toArgb()));
  }

  public void setSaturation(float saturation) {
    this.saturation = Math.max(0f, Math.min(1f, saturation));
    invalidate();
    if (colorListener != null) {
      colorListener.onColorChanged(getCurrentColor());
    }
  }

  public void setCurrentAngle(float angle) {
    this.currentAngle = angle % 360;
    invalidate();
  }

  public void setOnColorChangedListener(OnColorChangedListener listener) {
    this.colorListener = listener;
  }

  public float getCurrentAngle() {
    return currentAngle;
  }

  public float getSaturation() {
    return saturation;
  }

  public float[] getHSV() {
    float[] hsv = new float[3];
    Color.colorToHSV(getCurrentColor().toArgb(), hsv);
    return hsv;
  }
}
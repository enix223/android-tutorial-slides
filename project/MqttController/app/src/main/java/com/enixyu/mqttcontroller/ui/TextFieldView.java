package com.enixyu.mqttcontroller.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.enixyu.mqttcontroller.R;

public class TextFieldView extends LinearLayout {

  private TextView mLabel;
  private EditText mEditText;
  private TextView mRequiredTextView;

  public TextFieldView(Context context) {
    super(context);
    init(context, null);
  }

  public TextFieldView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public TextFieldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public TextFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  public String getValue() {
    return mEditText.getText().toString();
  }

  public void setLabel(String label) {
    mLabel.setText(label);
  }

  public void setPlaceholder(@Nullable String placeholder) {
    mEditText.setHint(placeholder);
  }

  public void setText(String value) {
    mEditText.setText(value);
  }

  public void setRequired(boolean required) {
    mRequiredTextView.setVisibility(required ? View.VISIBLE : View.GONE);
  }

  private void init(Context context, @Nullable AttributeSet attrs) {
    LayoutInflater.from(context).inflate(R.layout.text_field, this, true);
    mLabel = findViewById(R.id.label);
    mEditText = findViewById(R.id.edit_text);
    mRequiredTextView = findViewById(R.id.tv_star);
    if (attrs != null) {
      try (TypedArray typedArray = context.obtainStyledAttributes(attrs,
          R.styleable.TextFieldView)) {
        String label = typedArray.getString(R.styleable.TextFieldView_label);
        mLabel.setText(label);

        String placeholder = typedArray.getString(R.styleable.TextFieldView_placeholder);
        setPlaceholder(placeholder);

        boolean required = typedArray.getBoolean(R.styleable.TextFieldView_required, false);
        setRequired(required);
      }
    }
  }
}

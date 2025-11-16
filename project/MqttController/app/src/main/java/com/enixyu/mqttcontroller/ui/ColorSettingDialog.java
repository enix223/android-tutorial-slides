package com.enixyu.mqttcontroller.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.viewmodel.HomeViewModel;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class ColorSettingDialog extends DialogFragment implements
    ColorRingView.OnColorChangedListener,
    SeekBar.OnSeekBarChangeListener {

  private ColorRingView mColorPicker;
  private SeekBar mBrightnessSeekbar;
  private final HomeViewModel mViewModel;
  private final List<Disposable> disposables = new ArrayList<>();

  public ColorSettingDialog(HomeViewModel viewModel) {
    this.mViewModel = viewModel;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View view = requireActivity().getLayoutInflater()
        .inflate(R.layout.fragment_color_setting, null);

    mColorPicker = view.findViewById(R.id.color_picker);
    mColorPicker.setOnColorChangedListener(this);
    disposables.add(mViewModel.getColor()
        .skip(1)
        .subscribe(color -> mColorPicker.setColor(color)));
    mBrightnessSeekbar = view.findViewById(R.id.brightness);
    mBrightnessSeekbar.setOnSeekBarChangeListener(this);
    disposables.add(mViewModel.getBrightness()
        .subscribe(brightness -> mBrightnessSeekbar.setProgress(brightness)));

    AlertDialog.Builder builder = new Builder(getActivity(), R.style.ColorSettingDialog);
    builder.setView(view);
    return builder.create();
  }

  @Override
  public void onDestroy() {
    for (Disposable disposable : disposables) {
      disposable.dispose();
    }
    super.onDestroy();
  }

  @Override
  public void onColorChanged(Color color) {

  }

  @Override
  public void onColorSelected(Color color) {
    mViewModel.setLightColor(color);
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
    mViewModel.setBrightness(mBrightnessSeekbar.getProgress());
  }
}

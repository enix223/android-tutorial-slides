package com.enixyu.fileoperationdemo.db;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Todo implements Parcelable {

  ///  记录ID
  private int id;

  /// 代办事项
  private String title;

  /// 是否已完成
  private boolean done;

  public Todo(String title, boolean done) {
    this.title = title;
    this.done = done;
  }

  protected Todo(Parcel in) {
    id = in.readInt();
    title = in.readString();
    done = in.readByte() != 0;
  }

  public static final Creator<Todo> CREATOR = new Creator<>() {
    @Override
    public Todo createFromParcel(Parcel in) {
      return new Todo(in);
    }

    @Override
    public Todo[] newArray(int size) {
      return new Todo[size];
    }
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(title);
    parcel.writeByte((byte) (done ? 1 : 0));
  }
}

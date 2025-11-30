package com.enixyu.file.ui;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.enixyu.file.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity  {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
      checkPermission();
  }

    public void onRawClick(View view) {
      InputStream inputStream = null;
      Reader reader = null;
      BufferedReader bufferedReader = null;

      try {
          //得到资源中的Raw数据流
          inputStream = getResources().openRawResource(R.raw.test);
          reader = new InputStreamReader(inputStream);// 字符流
          bufferedReader = new BufferedReader(reader); //缓冲流
          StringBuilder result = new StringBuilder();
          String temp;
          while ((temp = bufferedReader.readLine()) != null) {
              result.append(temp);
          }
          Log.i("MainActivity", "result:" + result);
          Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              try {
                  reader.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (inputStream != null) {
              try {
                  inputStream.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (bufferedReader != null) {
              try {
                  bufferedReader.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }

    public void onAssetsClick(View view) {
      InputStream inputStream = null;
      Reader reader = null;
      BufferedReader bufferedReader = null;
      try {
          //得到资源中的asset数据流
          inputStream = getResources().getAssets().open("test.txt");
          reader = new InputStreamReader(inputStream);// 字符流
          bufferedReader = new BufferedReader(reader); //缓冲流
          StringBuilder result = new StringBuilder();
          String temp;
          while ((temp = bufferedReader.readLine()) != null) {
              result.append(temp);
          }
          Log.i("MainActivity", "result:" + result);
          Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              try {
                  reader.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (inputStream != null) {
              try {
                  inputStream.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (bufferedReader != null) {
              try {
                  bufferedReader.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }

    public void onDataNewWriteClick(View view) {

          FileOutputStream fileOutputStream = null;
          try {
              String text = "hello world";
              fileOutputStream = openFileOutput("test.txt", MODE_PRIVATE);
              fileOutputStream.write(text.getBytes());
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              if (fileOutputStream != null) {
                  try {
                      fileOutputStream.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }


  }

  public void  onDataOldWriteClick(View view) {
            String text = "hello world";
            File file1=new File(getFilesDir().getParent(),"test.txt");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file1);
                fileOutputStream.write(text.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

  }

    public void onDataNewReadClick(View view) {
          FileInputStream fileInputStream = null;
          Reader reader = null;
          BufferedReader bufferedReader = null;
          try {
              fileInputStream = openFileInput("test.txt");
              reader = new InputStreamReader(fileInputStream);// 字符流
              bufferedReader = new BufferedReader(reader); //缓冲流
              StringBuilder result = new StringBuilder();
              String temp;
              while ((temp = bufferedReader.readLine()) != null) {
                  result.append(temp);
              }
              Log.i("MainActivity", "result:" + result);
              Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              if (reader != null) {
                  try {
                      reader.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if (fileInputStream != null) {
                  try {
                      fileInputStream.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if (bufferedReader != null) {
                  try {
                      bufferedReader.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }

  }


  public  void onDataOldReadClick(View view) {
      InputStream inputStream = null;
      Reader reader = null;
      BufferedReader bufferedReader = null;
      try {

          File file=new File(getFilesDir().getParent(), "test.txt");
          inputStream = new FileInputStream(file);
          reader = new InputStreamReader(inputStream);
          bufferedReader = new BufferedReader(reader);
          StringBuilder result = new StringBuilder();
          String temp;
          while ((temp = bufferedReader.readLine()) != null) {
              result.append(temp);
          }
          Log.i("MainActivity", "result:" + result);
          Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              try {
                  reader.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (inputStream != null) {
              try {
                  inputStream.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (bufferedReader != null) {
              try {
                  bufferedReader.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

      }
  }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    public void onWriteClick(View view) {
        writeSdcard();
    }

    public void onReadClick(View view) {
        readSdcard();
    }

    private void writeSdcard() {
        String text = "hello world";

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File storage = Environment.getExternalStorageDirectory();
            File tempFile = new File(storage.getPath());

            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }

            File file1 = new File(tempFile, "test.txt");
            if (!file1.exists()) {
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file1);
                fileOutputStream.write(text.getBytes());
                Toast.makeText(this, "文件写入成功！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void readSdcard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            InputStream inputStream = null;
            Reader reader = null;
            BufferedReader bufferedReader = null;

            try {
                File storage = Environment.getExternalStorageDirectory();
                File tempFile = new File(storage.getPath());

                File file = new File(tempFile, "test.txt");
                inputStream = new FileInputStream(file);
                reader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(reader);

                StringBuilder result = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    result.append(temp);
                }

                Log.i("MainActivity", "result: " + result);
                Toast.makeText(this, "读取到内容：" + result, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "读取失败！", Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    if (reader != null) reader.close();
                    if (inputStream != null) inputStream.close();
                    if (bufferedReader != null) bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "已授权成功！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "授权被拒绝！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

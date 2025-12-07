package com.enixyu.sqlite.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.enixyu.sqlite.R;
import com.enixyu.sqlite.model.Student;
import com.enixyu.sqlite.sql.DBDao;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnInsert, btnQuery, btnClear, btnInfo, btnUpdate;
    private TextView tvResult;
    private DBDao dbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据库操作类
        dbDao = DBDao.getInstance(this);

        initViews();
        setupClickListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 如果是App的最后一个Activity，可以关闭数据库
        // 但通常建议在Application中管理
        dbDao.close();
    }

    private void initViews() {
        btnInsert = findViewById(R.id.btn_insert);
        btnQuery = findViewById(R.id.btn_query);
        btnClear = findViewById(R.id.btn_clear);
        btnUpdate = findViewById(R.id.btn_update);
        btnInfo = findViewById(R.id.btn_info);
        tvResult = findViewById(R.id.tv_result);
    }

    private void setupClickListeners() {
        // 插入数据按钮
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTestData();
            }
        });

        // 查询数据按钮
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryAllData();
            }
        });

        // 清空数据按钮
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllData();
            }
        });

        // 查看数据库信息按钮
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatabaseInfo();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFirstStudent();
            }
        });
    }

    /** 插入测试数据 */
    @SuppressLint("SetTextI18n")
    private void insertTestData() {
        // 创建一些测试学生数据
        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(20);
        student1.setSex("男");
        student1.setGrade("大三");
        student1.setStore("计算机学院");

        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(19);
        student2.setSex("女");
        student2.setGrade("大二");
        student2.setStore("文学院");

        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(21);
        student3.setSex("男");
        student3.setGrade("大四");
        student3.setStore("经济学院");

        // 插入数据
        dbDao.insert(student1);
        dbDao.insert(student2);
        dbDao.insert(student3);

        tvResult.setText("成功插入3条测试数据！\n" +
                "1. 张三, 20岁, 男, 大三, 计算机学院\n" +
                "2. 李四, 19岁, 女, 大二, 文学院\n" +
                "3. 王五, 21岁, 男, 大四, 经济学院");
    }

    /** 查询所有数据 */
    private void queryAllData() {
        List<Student> students = dbDao.query();

        if (students.isEmpty()) {
            tvResult.setText("数据库中没有数据！");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append("共查询到 ").append(students.size()).append(" 条数据：\n\n");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            result.append("第 ").append(i + 1).append(" 条记录：\n")
                    .append("姓名：").append(student.getName()).append("\n")
                    .append("年龄：").append(student.getAge()).append("\n")
                    .append("性别：").append(student.getSex()).append("\n")
                    .append("年级：").append(student.getGrade()).append("\n")
                    .append("学院：").append(student.getStore()).append("\n\n");
        }

        tvResult.setText(result.toString());
    }

    /** 清空所有数据 */
    private void clearAllData() {
        dbDao.clearAll();
        tvResult.setText("已清空数据库中的所有数据！");
    }


    /** 修改第一条数据 */
    @SuppressLint("SetTextI18n")
    private void updateFirstStudent() {
        // 获取第一条数据的ID
        int firstId = dbDao.getFirstStudentId();
        if (firstId == -1) {
            tvResult.setText("数据库中没有数据，请先插入数据！");
            return;
        }

        // 获取要修改的学生对象
        Student student = dbDao.getStudentById(firstId);
        if (student == null) {
            tvResult.setText("未找到要修改的数据！");
            return;
        }

        // 修改学生信息
        student.setName("修改后的姓名");
        student.setAge(25);
        student.setGrade("研究生");
        student.setStore("修改后的学院");

        // 执行更新
        boolean success = dbDao.updateStudent(student);
        if (success) {
            tvResult.setText("成功修改第一条数据！\n" +
                    "修改前：ID=" + firstId + "\n" +
                    "修改后：姓名=" + student.getName() +
                    ", 年龄=" + student.getAge() +
                    ", 年级=" + student.getGrade() +
                    ", 学院=" + student.getStore());
        } else {
            tvResult.setText("修改数据失败！");
        }
    }

    /** 显示数据库信息 */
    private void showDatabaseInfo() {
        StringBuilder result = new StringBuilder();
        result.append("数据库信息：\n\n");

        // 显示所有表名
        List<String> tables = dbDao.tablesInDB();
        result.append("数据库中的表：\n");
        for (String table : tables) {
            result.append(" - ").append(table).append("\n");
        }

        result.append("\n");

        // 显示数据库文件列表
        String[] dbFiles = dbDao.databaseList(this);
        result.append("数据库文件：\n");
        for (String dbFile : dbFiles) {
            result.append(" - ").append(dbFile).append("\n");
        }

        tvResult.setText(result.toString());
    }
}
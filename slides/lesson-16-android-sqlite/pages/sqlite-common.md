# Android SQLite常用类

- SQLiteDatabase数据库管理类（直接对数据库进行操作）

- SQLiteDatabase是SQLite的数据库管理类，开发者可以在活动页面代码或任何能取到Context的地方获取数据库实例。然后通过SQLiteDatabase提供的一些API来对数据库进行操作

- SQLiteOpenHelper 管理数据库的元数据，版本管理，创建数据库连接

---

# SQLiteOpenHelper

### 使用方法

1. 新建一个继承自SQLiteOpenHelper的数据库操作类，提示重写`onCreate`和`onUpgrade`两个方法。

2. 其中，`onCreate`方法只在第一次打开数据库时执行，在此可进行表结构创建的操作；

3. `onUpgrade`方法在数据库版本升高时执行，因此可以在`onUpgrade`函数内部根据新旧版本号进行表结构变更处理

4. 其中`onCreate()`是在数据库首次创建的时候调用，`onUpgrade()`是在数据库版本号DB_VERSION升级的时候才会调用

---

# SQLiteOpenHelper

```java
public class MySqliteHelper extends SQLiteOpenHelper {
     public MySqliteHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
　　　　　db.execSQL("CREATE TABLE IF NOT EXISTS student(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // TODO 更改数据库版本的操作,根据新旧版本号进行表结构变更处理，当打开数据库时传入版本号与当前不同会调用此方法
　　　　//在使用中只需要调用构造函数时把版本号参数version改大即可
       onCreate(db);
  }

　　@Override
　　public void onOpen(SQLiteDatabase db) {
　　　　super.onOpen(db); // TODO 每次成功打开数据库后首先被执行
　　}
}
```

---

# 插入数据INSERT

```java
// 1. 获取数据库连接
SQLiteDatabase db = dbHelper.getWritableDatabase();

// 2. 创建要写入的字段以及对应的值映射表
ContentValues values = new ContentValues();
values.put("stu_name", "张三");
values.put("stu_age", 21);
values.put("stu_sex", "男");
values.put("stu_grade", "A");

// 3. 执行写入
db.insert("student", null, values);

// 4. 关闭连接
db.close();
```

---

# 更新数据UPDATE

```java
// 1. 获取数据库连接
SQLiteDatabase db = dbHelper.getWritableDatabase();

// 2. 构造要修改的字段名及其对应的值映射表
ContentValues values = new ContentValues();
values.put("stu_name", "张三");
values.put("stu_age", 21);
values.put("stu_sex", "男");
values.put("stu_grade", "A");

// 3. 执行更新
// 筛选条件是id = 1
int rowsAffected = db.update("student", values, "id = ?", new String[]{"1"});

// 4. 关闭连接
db.close();
```

---

# 删除记录DELETE

```java
// 1. 获取数据库连接
SQLiteDatabase db = dbHelper.getWritableDatabase();

// 2. 执行删除 (id = 1)
db.delete("student", "id = ?", new String[]{"1"});

// 3. 关闭连接
db.close();
```

---

# 查询记录SELECT

```java
// 1. 获取数据库读连接
SQLiteDatabase db = dbHelper.getReadableDatabase();

// 2. 执行查询
Cursor cursor = db.rawQuery("SELECT stu_name, stu_age, stu_sex, stu_grade FROM student", null);

// 3. 遍历结果
while (cursor.moveToNext()) {
    // 姓名
    String name = cursor.getString(0);
    // 年龄
    int age = cursor.getInt(1);
    // 性别
    String sex = cursor.getString(2);
    // 评级
    String grade = cursor.getString(3);
}

// 4. 关闭连接
db.close();
```

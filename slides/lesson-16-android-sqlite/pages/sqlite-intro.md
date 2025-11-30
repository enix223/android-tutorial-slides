# 什么是sqlite数据库

- SQLite，是一款轻型的数据库，它的设计目标是嵌入式的，而且已经在很多嵌入式产品中使用了它，它占用资源非常的低，在嵌入式设备中，可能只需要几百K的内存就够了。它能够支持Windows/Linux/Unix等等主流的操作系统，同时能够跟很多程序语言相结合，同样比起Mysql、PostgreSQL这两款开源的世界著名数据库管理系统来讲，它的处理速度比他们都快。

1. 简单，无需安装。

SQLite 没有复杂的安装配置过程，不需要另外配置、安装或管理。不需要启动、停止或配置的服务器进程，无需创建新的数据库实例或为用户分配访问权限的“设置”过程。

2. 资源占用少。

SQLite占用空间非常小，是轻量级的，完全配置时小于600KiB，省略可选功能配置时小于250KiB，占用的电脑资源少，真正做到绿色、轻盈！

3. 处理速度快

SQLite比一些流行的数据库在大部分普通数据库操作要快！

---

# 作为Android 本地存储有什么优势

1. 效率高：查询和写入速度快。

2. 适合结构化数据：可存储用户信息、配置表、日志等。

3. 跨 Activity / 跨应用数据传递：数据库文件可直接访问。

4. 轻量级、无服务器：节省资源，不需要维护服务进程。

5. 可自由备份和复制：所有数据在一个文件中。

---

# 需要了解的SQLite数据库知识点

- SQLiteDatabase数据库管理类（直接对数据库进行操作）

- SQLiteDatabase是SQLite的数据库管理类，开发者可以在活动页面代码或任何能取到Context的地方获取数据库实例。然后通过SQLiteDatabase提供的一些API来对数据库进行操作：

// 创建名叫test.db的数据库。数据库如果不存在就创建它，如果存在就打开它 SQLiteDatabase db = openOrCreateDatabase(getFilesDir() + "/test.db", Context.MODE_PRIVATE, null);

// 删除名叫test.db数据库 // deleteDatabase(getFilesDir() + "/test.db");

- 调用该类API要用到SQL语句，SQLite的多数SQL语法与Oracle一样，可以到网上查阅

- SQLiteDatabase中常用的API：

---

<div class="p-6 border rounded shadow text-left w-[600px]">
  <h2 class="text-2xl font-bold mb-4">管理类（数据库层面）</h2>
  <ul class="leading-7">
    <li><b>openDatabase</b>：打开指定路径的数据库。</li>
    <li><b>isOpen</b>：判断数据库是否已打开。</li>
    <li><b>close</b>：关闭数据库。</li>
    <li><b>getVersion</b>：获取数据库版本号。</li>
    <li><b>setVersion</b>：设置数据库版本号。</li>
  </ul>
</div>

<br />

<div class="p-6 border rounded shadow text-left w-[600px]">
  <h2 class="text-2xl font-bold mb-4">事务类（事务层面）</h2>
  <ul class="leading-7">
    <li><b>beginTransaction</b>：开始事务。</li>
    <li><b>setTransactionSuccessful</b>：设置事务成功标志。</li>
    <li><b>endTransaction</b>：结束事务，判断提交或回滚。</li>
  </ul>
</div>

---

<div class="p-6 border rounded shadow text-left w-[600px]">
  <h2 class="text-2xl font-bold mb-4">数据处理类（数据表层面）</h2>
  <ul class="leading-7">
    <li><b>execSQL</b>：执行 SQL（建表、删表、结构变更）。</li>
    <li><b>delete</b>：删除符合条件记录。</li>
    <li><b>update</b>：更新符合条件记录。</li>
    <li><b>insert</b>：插入记录。</li>
    <li><b>query</b>：执行查询，返回 Cursor。</li>
    <li><b>rawQuery</b>：执行原生 SQL，返回 Cursor。</li>
  </ul>
</div>

<br />

- 但是直接通过SQLiteDatabase进行操作数据库非常不方便，必须小心不能重复地打开数据库，处理数据库的升级也很不方便。

- 因此Android提供了一个辅助工具—— SQLiteOpenHelper，我们可以通过SQLiteOpenHelper这个数据库帮助器来安全方便地打开、升级数据库

---

# SQLite数据类型

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">描述</div>
  </div>

  <!-- 数据行：SQLite 实际支持 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">NULL</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">空值类型。</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">INTEGER</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">整数类型，根据数值大小自动使用 1~8 字节存储。</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">REAL</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">浮点数类型，使用 8 字节 IEEE 754 格式存储。</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">TEXT</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">文本类型，以 UTF-8、UTF-16BE 或 UTF-16LE 格式存储。</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">BLOB</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">二进制数据，按输入的原始格式存储。</div>
  </div>

</div>

---

<div style="display: table; border-collapse: collapse; width: 100%;">
   <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">描述</div>
  </div>

  <!-- 数据行：SQLite 可写但不真正限制的类型 -->
  <div style="display: table-row; background-color: #fafafa;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">VARCHAR(n)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">
      实际等同于 TEXT，SQLite 不会限制长度，也不会验证 n。
    </div>
  </div>

  <div style="display: table-row; background-color: #fafafa;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">CHAR(n)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">
      实际等同于 TEXT，不会强制固定长度。
    </div>
  </div>

  <div style="display: table-row; background-color: #fafafa;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">DATE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">
      SQLite 没有原生日期类型。通常以 TEXT（ISO8601）、INTEGER（时间戳）或 REAL（Julian Day）存储。
    </div>
  </div>

  <div style="display: table-row; background-color: #fafafa;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">TIME</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">
      同 DATE，SQLite 不支持原生时间类型，实际以 TEXT/INTEGER/REAL 存储。
    </div>
  </div>

</div>

---

# 数据库SQL增删改查

- 查找

```sql
select column1,column2 from table_name
或者
select * from table_name
```

- 插入

```sql
insert into table_name(column1,column2) values (value1,value2)
```

- 更新

```sql
update table_name set column1 = value1, column2 = value2
where +条件语句
```

- 删除

```sql
delete from table_name where +条件语句
```

---

- SQLiteOpenHelper数据库帮助器（安全方便地打开、升级数据库）

- 使用方法：

1. 新建一个继承自SQLiteOpenHelper的数据库操作类，提示重写onCreate和onUpgrade两个方法。

2. 其中，onCreate方法只在第一次打开数据库时执行，在此可进行表结构创建的操作；

3. onUpgrade方法在数据库版本升高时执行，因此可以在onUpgrade函数内部根据新旧版本号进行表结构变更处理

4. 其中onCreate()是在数据库首次创建的时候调用，onUpgrade()是在数据库版本号DB_VERSION升级的时候才会调用

- 例如：

```java
//数据库帮助器SQLiteOpenHelper
public class MySqliteHelper extends SQLiteOpenHelper {

     public MySqliteHelper(Context context) {
        super(context, "test.db", null, 1);
    }
```

---

```java

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
　　　　　db.execSQL("CREATE TABLE IF NOT EXISTS student(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // TODO 更改数据库版本的操作,根据新旧版本号进行表结构变更处理，当打开数据库时传入版本号与当前不同会调用此方法
　　　　//在使用中只需要调用构造函数时把版本号参数version改大即可
　　　　db.execSQL("DROP TABLE IF EXISTS student");
       onCreate(db);
  }

　　@Override
　　public void onOpen(SQLiteDatabase db) {
　　　　super.onOpen(db); // TODO 每次成功打开数据库后首先被执行
　　}
}
```

---

- 数据库操作的工具类（封装保证数据库安全的必要方法和操作数据库记录的方法

- 在这个类中，封装保证数据库安全的必要方法，包括获取单例对象、打开数据库连接、关闭数据库连接，并且封装对表记录进行增加、删除、修改、查询的操作方法。

- 获取单例对象：确保App运行时数据库只被打开一次，避免重复打开引起错误。

- 打开数据库连接：SQLite有锁机制，即读锁和写锁的处理；故而数据库连接也分两种，读连接可调用SQLiteOpenHelper的getReadableDatabase方法获得，写连接可调用getWritableDatabase获得。

- 关闭数据库连接：数据库操作完毕后，应当调用SQLiteDatabase对象的close方法关闭连接。

- 例如：

```java
/**
 * DbManger 操作我们数据库的工具类  我们一般写成单例模式
 * 单例模式 ：  在整个应用程序中  不管什么地方（类）  获得的都是同一个对象实例*/
public class DbManager {
    private static DbManager instance;
    private MySqliteHelper helper;

    private DbManager(Context context){
        helper = new MySqliteHelper(context.getApplicationContext());
    }
```

---

```java
    public static synchronized DbManager getInstance(Context context){
        if(instance == null){
            instance = new DbManager(context);
        }
        return instance;
    }

    public void addData(String name, int age){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        db.insert("student", null, cv);
        db.close();
    }
}

```

# 实例

- 首先创建一个类去继承SQLiteOpenHelper，创建一个test.db的数据库

```java
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";//数据库名称
    public static final int DB_VERSION = 1;//数据库版本


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DBDao.SQL_CREATE_TABLE);//建表
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }
}
```

- 其中onCreate()是在数据库首次创建的时候调用，onUpgrade()是在数据库版本号DB_VERSION升级的时候才会调用，这里如果你想在数据中添加表，你只能不断的升级数据库的版本好调用onUpgrade()！！！！

---

- 建表和对数据库实现增删改查

```java
public class DBDao {

    public static final String TABLE_NAME = "student";

    private static final String ID = "id";
    private static final String NAME = "stu_name";
    private static final String AGE = "stu_age";
    private static final String SEX = "stu_sex";
    private static final String GRADE = "stu_grade";
    private static final String STORE = "store";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " TEXT," +
            AGE + " INTEGER," +
            SEX + " TEXT," +
            GRADE + " TEXT," +
            STORE + " TEXT" +
            ")";

    private static volatile DBDao instance;  // ✅ 加volatile防止指令重排
    private DBHelper dbHelper;
    
    /** 双重检查锁定 */
    public static DBDao getInstance(Context context) {
         if (instance == null) {                     // 第一次检查（不加锁）

```

---

```java
            synchronized (DBDao.class) {            // 加锁
                if (instance == null) {             // 第二次检查（加锁后）
                    instance = new DBDao(context.getApplicationContext());
                }
            }
        }
        return instance;
    }
    private DBDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    /** 插入数据 */
    public void insert(Student student) {
        if (student == null) return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(NAME, student.getName());
            cv.put(AGE, student.getAge());
            cv.put(SEX, student.getSex());
            cv.put(GRADE, student.getGrade());
            cv.put(STORE, student.getStore());
            db.insert(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
```

---

```java
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /** 删除表中所有数据 */
    public void clearAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /** 查询所有数据 */
    @SuppressLint("Range")
    public List<Student> query() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {

```

---

```java
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Student student = new Student();
                student.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                student.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
                student.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                student.setGrade(cursor.getString(cursor.getColumnIndex(GRADE)));
                student.setStore(cursor.getString(cursor.getColumnIndex(STORE)));
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return list;
    }

    /** 查询数据库中的表名 */
    public List<String> tablesInDB() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

```

---

```java
            while (cursor.moveToNext()) {
                list.add(cursor.getString(0));
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return list;
    }

    /** 获取数据库文件列表 */
    public String[] databaseList(Context context) {
        return context.databaseList();
    }
    
    /** 关闭数据库连接（在App退出时调用） */
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}

```

---

```java
public class MainActivity extends AppCompatActivity {

    private DBDao dbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取单例时传入 Context
        dbDao = DBDao.getInstance(this);

        Student student = new Student("小明", 100, "三年二班", "男", "100");
        dbDao.insert(student);

        List<Student> students = dbDao.query();
        Log.e("MainActivity", "数据条数: " + students.size());
        if (!students.isEmpty()) {
            Log.e("MainActivity", "第一条数据: " + students.get(0).toString());
        }

        Log.e("MainActivity", "表格列表: " + dbDao.tablesInDB());
        Log.e("MainActivity", "数据库文件列表: " + Arrays.toString(dbDao.databaseList(this)));
    }
}

```

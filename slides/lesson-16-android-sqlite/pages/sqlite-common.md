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

- 创建实体Student 类“完整版”

```java
public class Student {
  private String name;
  private int age;
  private String grade;
  private String sex;
  private String store;

  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

```

---

```java

  public void setAge(int age) {
    this.age = age;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Student() {

  }


```

---

```java

  public Student(String name, int age, String grade, String sex,String store) {
    this.name = name;
    this.age = age;
    this.grade = grade;
    this.sex = sex;
    this.store=store;
  }

  @NonNull
  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", grade='" + grade + '\'' +
        ", sex='" + sex + '\'' +
        ", store='" + store + '\'' +
        '}';
  }
}
```

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

    private static DBDao instance;
    private DBHelper dbHelper;

    /** 获取单例实例，第一次调用需传入 Context */
    public static synchronized DBDao getInstance(Context context) {
        if (instance == null) {

```

---

```java
            instance = new DBDao(context.getApplicationContext());
        }
        return instance;
    }

    /** 私有构造函数 */
    private DBDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    /** 插入数据 */
    public synchronized void insert(Student student) {
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
        } finally {
```

---

```java
            db.endTransaction();
            db.close();
        }
    }

    /** 删除表中所有数据 */
    public synchronized void clearAll() {
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
    public synchronized List<Student> query() {
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

# ListView 示例

- 创建 my_list.xml

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@+id/my_list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </ListView>

</LinearLayout>
```

---

- 创建一个item的布局文件 my_sm_list_item.xml

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    >
  <ImageView
    android:id="@+id/img"
    android:layout_width="60dp"
    android:layout_height="60dp"
    android:layout_alignParentStart="true"
    android:layout_centerVertical="true"
    android:padding="5dp" />

  <TextView
    android:id="@+id/name"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerVertical="true"
    android:layout_toEndOf="@+id/img"
    android:layout_marginStart="10dp"
    android:padding="30dp"
    android:textColor="@color/black" />
</RelativeLayout>
```

---

- 在Activity用ListView绑定Adapter

```java

public class MainActivity extends AppCompatActivity{

    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list);
        listView = findViewById(R.id.my_list_view);
        String[] arr = {"菠萝", "芒果", "石榴", "葡萄","西瓜","哈密瓜","菠萝", "芒果", "石榴", "葡萄","西瓜"
        ,"哈密瓜","菠萝", "芒果", "石榴", "葡萄","西瓜","哈密瓜"};

//ArrayAdapter适配器
//ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", arr[i]);
            map.put("img", String.valueOf(R.drawable.ic_launcher_background));
            list.add(map);
        }
//SimpleAdapter适配器
//使用SimpleAdapter来作为ListView的适配器，比ArrayAdapter能展现更复杂的布局效果。为了显示较为复杂的ListView的item效果，需要写一个xml布局文件，来设置ListView中每一个item的格式。
//SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.my_sm_list_item,new String[]{"name","img"},new int[]{R.id.name,R.id.img});

```

---

```java
        //自定义BaseAdapter适配器
        MyAdapter adapter = new MyAdapter(this,R.layout.my_sm_list_item,list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arr[position], Toast.LENGTH_SHORT).show();
            }
        });

    }

```

- 下面是一个自定义的Adapter

```java
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Map<String, String>> mList;
    private int mViewId;

    public MyAdapter(Context context,int viewId,List<Map<String, String>>list) {
        this.mContext = context;
        this.mList = list;
        this.mViewId = viewId;
    }
```

---

```java
    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(mViewId, null);
        TextView nameTX = convertView.findViewById(R.id.name);
        ImageView imageV = convertView.findViewById(R.id.img);
        nameTX.setText(mList.get(position).get("name"));
        imageV.setImageResource(Integer.parseInt(mList.get(position).get("img")));

        return convertView;
    }
    class ViewHolder {
        private TextView nameTX;
        private ImageView imageV;
    }

}
```

---

# ListView的优化

- 目前我们ListView的运行效率是很低的，因为在自定义的Adapter的 getView()方法中，每次都将布局重 新加载了一遍，将快速滚动的时候， 这将会成为性能的瓶颈。这里我们可以利用重用机制去优化。

<br />
<br />

<div class="flex flex-col items-center justify-center">
    <img src="/android-listview-1.png" width="500"/>
</div>

---

```java

 @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(mViewId, null);
            viewHolder.nameTX = convertView.findViewById(R.id.name);
            viewHolder.imageV = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.nameTX.setText(mList.get(position).get("name"));
        viewHolder.imageV.setImageResource(Integer.parseInt(mList.get(position).get("img")));
        return convertView;
    }

    class ViewHolder {
        private TextView nameTX;
        private ImageView imageV;
    }
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-listview-2.gif" width="250"/>
</div>

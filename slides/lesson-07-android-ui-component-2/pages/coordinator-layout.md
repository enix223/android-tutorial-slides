
## CoordinatorLayout

- CoordinatorLayout翻译为协调者布局，是在 Google IO/15 大会发布的，是用来协调其子View们之间动作的一个容器，遵循Material Design风格，包含在 com.android.support:design中。CoordinatorLayout是一个超级强大的FrameLayout，结合AppBarLayout、 CollapsingToolbarLayout等可产生各种炫酷的效果。

- CoordinatorLayout须要作为顶层父View，子View想要与CoordinatorLayout实现"联动性"效果的首要条件是这个View必须实现了NestedScrollingChild接口(例如：NestedScrollView、RecyclerView等控件)。CoordinatorLayout子控件如果需要联动，需要设置app:layout_behavior属性，AppBarLayout没有设置是因为本身有个默认的app:layout_behavior查看源码如下：

```java
@CoordinatorLayout.DefaultBehavior(AppBarLayout.Behavior.class)
public class AppBarLayout extends LinearLayout {
```

---

- xml 示例：

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:layout_width="match_parent"
  android:layout_height="match_parent">
  <com.google.android.material.appbar.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <TextView
      android:layout_width="match_parent"
      android:layout_height="100dp"
      android:background="#222222"
      android:gravity="center"
      android:text="该区域可折叠"
      android:textColor="@android:color/white"
      android:textSize="30sp"
      app:layout_scrollFlags="scroll" />
    <TextView
      android:layout_width="match_parent"
      android:layout_height="50dp"
      android:background="#DD012D"
      android:gravity="center"
      android:text="该区域为上滑至头部固定区域"
      android:textColor="@android:color/white"
      android:textSize="20sp" />
  </com.google.android.material.appbar.AppBarLayout>
```
---

```xml
  <androidx.core.widget.NestedScrollView
    android:id="@+id/rv_demo1_content"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:layout_behavior="@string/appbar_scrolling_view_behavior">
    <TextView
      android:layout_width="match_parent"
      android:text="这是一个滚动布局"
      android:textSize="200sp"
      android:background="#00ff00"
      android:layout_height="wrap_content"/>
  </androidx.core.widget.NestedScrollView>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

---


<div class="flex flex-col items-center justify-center">
    <img src="/coordinator-layout.gif" width="300"/>
</div>

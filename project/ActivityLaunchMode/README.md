# Activity启动模式实验

## activity启动模式

* `standard`，每次跳转都创建一个实例
* `singleTop`, 每次打开如果栈顶已经存在该activity，则不再创建一个新的activity；如果目标activity不在栈顶，则创建一个新的实例
* `singleTask`，在本任务栈中保证只有一个activity实例
* `singleInstance`，每次跳转新建一个任务栈，并且该栈被目标activity独占
* `singleInstancePerTask`，允许其他activity加入到该任务栈中。

## 实验

| activity                      | 启动模式                  |
|-------------------------------|-----------------------|
| StandardActivity              | standard              |
| SingleTopActivity             | singleTop             |
| SingleTaskActivity            | singleTask            |
| SingleInstanceActivity        | singleInstance        |
| SingleInstancePerTaskActivity | singleInstancePerTask |
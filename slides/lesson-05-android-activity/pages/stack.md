# 任务栈 (Stack)
栈是一种先进后出（LIFO）的数据结构，被广泛应用于计算机的各个领域。

<img src="/hanoi-tower.jpeg" class="h-[50%]" />

<v-clicks>

* <span class="text-blue-700">栈</span>类似于汉诺塔游戏中的塔
* <span class="text-blue-700">栈顶</span>指的是塔上最上方的那个圆圈块，每次只能从塔的顶部拿出一个圆圈块，并放到其他柱子。
* 当叠加到柱子中的圆圈超过柱子的高度时，表示栈已满，再放入圆圈就会导致<span class="text-red-800">栈溢出 (stack overflow)</span>

</v-clicks>

---

# 入栈 Push
向栈中加入元素，这个动作叫入栈或压栈

<img src="/stack-push.png" class="h-[60%]" />

<v-clicks>

* 初始状态下，栈为空，栈顶指针指向栈的底部
* 当放入第一个元素 ❶ 后，栈顶指针向上移动一格，此时栈中有一个元素
* 当再次放入一个元素 ❷ 后，栈顶指针继续向上移动一格，指向元素2
* 放入第三个元素 ❸ 后，栈顶指针向上移动一格，指向元素3

</v-clicks>

---

# 出栈 Pop
从栈顶移除一个元素，这个动作叫做出栈

<img src="/stack-pop.png" class="h-[60%] mb-3" />

<v-clicks>

* 开始出栈前，栈中有3个元素，栈顶指针指向元素❸
* 从栈中依次弹出元素❸、❷、❶后，栈变为空，栈顶指针指向栈的底部。

</v-clicks>
# extends & super

> 问题引导

```java
二叉查找树 类的声明如下
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
Comparable 中的泛型 <? super AnyType> 有何作用？与 <? extends AnyType> 又有何区别？
```

> 名词解释

```java
<? extends T>: 上界通配符(Upper Bounds Wildcards)
<? super T>: 下界通配符(Lower Bounds Wildcards)
```

- 解决问题之前先定义一些类作为测试对象

> 定义类

```java
class Plate<T> {
	T item;
	Plate(T item) {this.item = item;}
}

class Food {}

class Fruit extends Food {}
class Meat extends Food {}

class Apple extends Fruit {}
```

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS4/pic/class.png)


> 为什么要引入通配符 & 边界

- 举个栗子

```
Fruit fruit = new Apple(); //Correct
Plate<Fruit> fruitPlate = new Plate<Apple>(new Apple()); //Error
```

- 显然，第一句是正确的

```
Apple IS-A Fruit
```

- 但第二句编译报错

```
- Type mismatch: cannot convert from Plate<Apple> to Plate<Fruit>
```
- 为什么？

```java
Plate<Apple> IS-NOT-A Plate<Fruit>

装 Apple 的盘子无法转换成装 Fruit 的盘子
容器里面的东西有继承，但容器本身没有继承关系

所以引入通配符 & 边界是为了更好的使用泛型
也就是让水果盘子也能当作苹果盘子使用
```

> 那么上下界又有什么区别？

- 首先声明——何为 ?

```
就是一个“问号”，匹配所有类型 AnyType | T | E
```

- e.g.

```java
Plate<?> p = new Plate<Fruit>(new Apple());  //盘子 p 可装任何一类东西，但不知道具体是什么
```

@有人可能会问，后面不是指定 Plate<Fruit> 了吗？

p 里面肯定是装 Fruit 滴！

- 实质上

```java
编译器将 Plate<Fruit> 当作 CAP#1 占位符看待
用来捕获 Fruit | Fruit子类，但不知道具体对象（不知道是 Apple 还是 Fruit）
也就是说，即使指定特定的对象，编译器也只是“敷衍地”给它打个"?"而已
```

> ? 后跟的 extends | super 有何区别？

- 先拓展上面定义的类 Plate

```java
class Plate<T> {
	T item;
	Plate(T item) {this.item = item;}
/*添加 set get 方法*/
	public void set(T t) {item = t;}
	public T get() {return item;}
}
```
## extends 

规定上界

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS4/pic/extends.png)

- 举栗子

```
Plate<? extends Fruit> fpe = new Plate<Fruit>(new Apple()); 
```
```
extends 规定上界，也就是说指定对象最高只能是 Fruit
如果指定 Fruit 基类
eg: new Plate<Food>(new Apple()); 
则报错
```
- get

```java
Fruit fruit = fpe.get(); //Correct
Food food = fpe.get(); //Correct
```
```java
fpe 中虽然不知道盘中为何物，但知道上界是 Fruit
所以，读取作为 Fruit | Fruit 基类（Food）并没问题
本质是：父类引用指向子类对象
```
- set

```java
fpe.set(new Apple()); //Error
fpe.set(new Fruit()); //Error
```
- 再次强调

```java
fpe 不知道盘中具体的类型 //? 仅是占位符，模糊/抽象化类型
所以指定一个实对象给一个虚对象，编译器肯定乱了，乱了只能报错

说白了，不知道盘子里究竟是什么水果的情况下，随便扔个苹果进去肯定违背了盘子的意愿
这时候编译器就要出来搞事情了
```

## super

规定下界

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS4/pic/super.png)

- e.g.

```
Plate<? super Fruit> fps = new Plate<Fruit>(new Apple()); 
```
```
super 规定下界，也就是指定对象最低只能是 Fruit
如果指定 Fruit 的子类
eg: new Plate<Apple>(new Apple());
同样报错
```
- get

```java
Fruit fruit = fps.get(); //Error
Food food = fps.get(); //Error
```
```java
同理，fps 虽然知道下界是 Fruit，但不知道盘中的具体类（不知道是 Fruit 还是 Food）
所以，读取出来的东西不知道什么鬼，贸然指定肯定不妥当
与 extends set 的说法相反，将虚对象指定给一个实际对象
同样，如果这样做，编译窗口就会蹦出一串串不明字体
```
- set

```java
fps.set(new Apple()); //Correct
fps.set(new Fruit()); //Correct
```
```
同理，fps 知道下界为 Fruit，那么扔个 Apple 什么的，并没有违背盘子的意愿，顶多就是替换了原有的水果
本质还是：父类引用指向子类对象
```

---

> p.s.

```
其实，无论是上界还是下界修饰，都可以使用 get
但 extends 使用 get 只能使用 Object（祖宗类）作为引用，并且会导致信息丢失
用大腿想一下就知道，一下子回到史前年代，究竟会丢了什么东西呢？！
这个有待商榷。。。

另一种处理方式，就是先使用 instanceof 判断一下，然后再强制转换类型
```

> ==Summary==

```
PECS：Producer Extends，Consumer Super
    <? extends T> 规定上界，只能 get，不能 set，生产者 读取
    <? super T>   规定下限，只能 set，不能 get，消费者 插入

get set
    父类引用指向子类对象，即多态三要素之一
```

- [x] 参考链接: [https://www.zhihu.com/question/20400700](https://www.zhihu.com/question/20400700)
# MinMaxHeap

最小-最大堆 (混合堆) [MinMaxHeap源码](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/src/MinMaxHeap.java)

> 简介

![简介](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/souce.png)

- 特点

    1. 集合 min & maxHeap 的功能 详细见 [BinaryHeap](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/src/BinaryHeap.java)
    2. 支持原有操作，并同时具备 deleteMin & Max 操作
    3. 同样使用数组实现
    3. 只有 root 元素时，root 即是最大值也是最小值
        \
        最小值永远在 root 位置，最大值总在 root 的儿子里

> 下标位置    

i | 2i | 2i+1
---|---|---
parent | leftChild | rightChild

> 奇偶层属性

even | odd |
---|---
minHeap | maxHeap

- ==keyImplMethod==
    
    1. percolateUp (evenDepthPercolateUp, oddDepthPercolateUp);
    2. percolateDown (minPercolateDown, maxPercolateDown);
    3. findIndex (findMinIndex, findMaxIndex);

- 注释缩写定义

    1. L: Local
    2. P: Parent
    3. GP: GrandParent

- class

    1. 元素数组 array[], 用于记录数值
    2. 构造方法进行 array & size 初始化

```java
public class MinMaxHeap<AnyType extends Comparable<AnyType>> {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private AnyType array[] = null;

	MinMaxHeap() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public MinMaxHeap(int defaultSize) {
		array = (AnyType[]) new Comparable[defaultSize];
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
		size = 0;
	}
	
}
```

- insert

    1. 插入元素 x
    2. 先扩容判断（容量调整）
    3. size 定位，制造空穴（hole）
    4. array[0] 作为哨兵，即临时值
        \
        好处：元素下标 index == size 大小一一对应
    5. 上滤调整
    
- [ ] insert example

- 声明

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert0.png)

- 1~3 插入比较简单，都是直接插入，如图所示

> insert 1

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert1.png)

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert2.png)

> insert 2

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert3.png)

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert4.png)

> insert 3

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert5.png)

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert6.png)

- 插入 4 涉及上滤，即与父节点进行交换

> 同样构造空穴

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert7.png)

> 4 扔进空穴位

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert8.png)

> 4 发现比父节点（2）大，需要做上滤处理

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert9.png)

> 处理结果，交换两者位置

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert10.png)

- 插入 5，同上

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert11.png)

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert12.png)

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert13.png)

- 插入 0，又有所不同

> 正常插入至末尾处

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert14.png)

> 此时虽然比父节点（3）小（符合最大堆的属性）
\
> 但却比祖父节点（1）小（违反最小元素在 top 的原则）

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert15.png)

> 所以与祖父节点做交换

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/insert16.png)

- 查看 [JDS6.pptx](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/JDS6.pptx) 83~99 页 更为直观（需下载观看）

- 代码实现

```java
public void insert(AnyType x) {
	if(++size == array.length-1) {
		ensureCapacity(array.length*2+1);
	}
	
	int hole = size;
	array[0] = x; //sentry
	
	percolateUp(array[0], hole);
}
```

- percolateUp

    1. 上滤处理
    2. 获取 hole/index 深度 depth
    3. 根据所在 depth 所属类型
        \
        偶数深度 (evenDepth) or 奇数深度 (oddDepth)
        \
        执行相应上滤 (percolateUp) 操作

```java
/**
 * 
 * @param index the position of array
 * @return int the index belongs to which depth
 */
private void percolateUp(AnyType x, int hole) {
	int depth = depth(hole);
	if(depth % 2 == 0) {
		evenDepthPercolateUp(x, hole);
	} else {
		oddDepthPercolateUp(x, hole);
	}
}
```

- depth

    1. 计算 index 所在深度 (depth)
    2. 使用==左移位运算== (<<) 计算所在层的最大节点数
    3. 当节点数 >= index 表明该 index 处于该 depth-1;
        \
        其中 root.depth == 0; 且根据层数由上往下递增

```java
/**
 * 
 * @param index the position of array
 * @return int the index belongs to which depth
 */
private int depth(int index) {
	int depth = 0;
	while(((1 << depth) - 1) < index) {
		depth++;
	}
	return depth-1;
}	
```

- evenDepthPercolateUp

    1. flag 用于判断 x 比 P 大或者小
    2. x 与 P 进行比较，根据大小情况做相应移位或者保持不动
    3. 根据 flag 属于 even or oddDepth 做相应处理；==特别强调，单向赋值==
        1. even: minHeap 属性，较小在上
        2. odd: maxHeap 属性，较大在上
        3. hole：空穴位置上移至 GP 位置，如果 GP 有进行下移的话
    4. 最终将 x 元素放置到空穴位

```java
/**
 * percolate up method for dealing with even depth case
 * @param x the element which for percolate up
 * @param hole the hole position of array OR the position where x belong
 */
private void evenDepthPercolateUp(AnyType x, int hole) {
	/*
	 * true == greater
	 * false == smaller
	 */
	boolean flag = false;
	
	/*
	 * greater or smaller than P
	 */
	if(hole / 2 > 0) { 
		if(x.compareTo(array[hole/2]) > 0) { //greater than P
			array[hole] = array[hole/2];
			hole /= 2; //hole jumps to P position, which is on odd level
			flag = true;
		} else {
			flag = false; //stay at L position
		}
	}
	
	/*
	 * compare with GP & move it when necessary
	 */
	while(hole / 4 > 0) {
		if(flag) { //odd level
			if(x.compareTo(array[hole/4]) > 0) { //greater than GP
				array[hole] = array[hole/4];
			} else {
				break;
			}
		} else { //even level
			if(x.compareTo(array[hole/4]) < 0) {
				array[hole] = array[hole/4];
			} else {
				break;
			}
		}
		hole /= 4; //move to GP position
	}
	
	array[hole] = x; //the element x assign to the hole position finally
}
```

- oddDepthPercolateUp

    操作与 evenDepthPercolateUp 极其相似
    \
    仅改变判断的符号方向，其余完全不变

- deleteMin

    1. 缩容判断处理
    2. 拿到最小值的位置
    3. array[size] 最后一个元素提取到首位 array[1];
    4. size--; 忽略被提取的元素
    5. 下滤处理

- [ ] deleteMin example

> 构建如下 MinMaxHeap，其符合最小最大堆的性质，读者可自行证明
\
> 这里 root 左边的子堆的元素比右边子堆的都小

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/deleteS0.png)

> 正常移除 root 元素，空出来的位置用最后一个元素顶替
\
> 分别找到左右子堆中的最小值，进行下滤处理

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/deleteS1.png)

> 但此时 5 比父节点（4）大（违反最大堆原则）
\
> 所以需要再将其上滤处理
\
> 也就是下滤处理的最后一步，要进行上滤处理

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/deleteS2.png)

> 上滤交换元素得到下图

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/deleteS3.png)

> 最后 return 一开始拿到的 root

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/pic/deleteS4.png)

- 详细见 PPT 110~114 页

- 代码实现

```java
public AnyType deleteMin() {
	if(size <= array.length/4) {
		ensureCapacity(array.length/2);
	}
	int minIndex = findMinIndex(1);
	AnyType min = array[minIndex];
	array[1] = array[size--];
	minPercolateDown(1);
	return min;
}
```

- findMinIndex

    1. 由 start 位置往下找 minIndex
    2. 如果有儿子
        1. start 属于 minHeap's root index
        \
        返回本位
        2. start 属于 maxHeap's root index
        \
        寻找儿子中的较小者
    3. 没有儿子，本位返回

```java
/**
 * find minimum index from start, which start from the heap or subheap's root
 * @param start find minimum index where it start
 * @return int the index of minimum element
 */
private int findMinIndex(int start) {
	if(isEmpty()) {
		throw new NoSuchElementException();
	}
	if(start*2 <= size) { //has kid
		if(array[start].compareTo(array[start*2]) < 0) { /*for minHeap case*/
			/*
			 * if (startNode < leftNode) --so that--> (startNode < rightNode) (if exist)
			 * which means left child as representative
			 */
			return start; //return the root's index == 1;
		} else if(start*2 < size) { /*for maxHeap case*/ 
			/*
			 * has right child | find minimum from maxHeap,  
			 */
			return array[start*2].compareTo(array[start*2+1]) < 0 ? 
					start*2 : start*2+1; 
		} else { 
			/*
			 * only left child
			 */
			return start*2;
		}
	}
	return start; //NO kids: only root & node without kids
}
```

- minPercolateDown

    1. 储存初始 index 的 element
    2. leftMinIndex & rightMinIndex 为 0 表没有该儿子
        \
        因为数组有效元素起始与 index == 1；
    3. while 只要 index 不出界
        1. 借助 findMinIndex 分别找到左右儿子的最小者（如果存在的话）
        2. 根据左右儿子存在情况进行下调处理 ==单向赋值==
    4. 起始元素 theElement 放置到最终 index 位
    5. theElement 上滤处理
        1. 下调是跳（儿子）层做单向赋值
        2. ==特殊情况== 孙子层元素比其父节点大（孙子层在奇数深度）
    
```java
/**
 * percolate down the element on index
 * @param index percolate down from where it start
 */
private void minPercolateDown(int index) {
	AnyType theElement = array[index];
	int leftMinIndex = 0, rightMinIndex = 0;
	
	while(index * 2 <= size) { //has child
		leftMinIndex = findMinIndex(index*2);
		
		if(index * 2 < size) { //has right child
			rightMinIndex = findMinIndex(index*2+1);
		} else {
			rightMinIndex = 0;
		}
		
		if(rightMinIndex == 0) { //only left child
			if(theElement.compareTo(array[leftMinIndex]) > 0) { //kid smaller than theElement
				array[index] = array[leftMinIndex];
				index = leftMinIndex;
			} else {
				break;
			}
		} else { //both left & right
			int nextIndex = array[leftMinIndex].compareTo(array[rightMinIndex]) < 0 ? 
					leftMinIndex : rightMinIndex; //default right
			if(theElement.compareTo(array[nextIndex]) > 0) { //kid smaller than theElement
				array[index] = array[nextIndex];
				index = nextIndex;
			} else {
				break;
			}
		}
	}
	
	array[index] = theElement;
	
	/*
	 * special case: allLeft < allRight
	 * deleteMin -> minPercolateDown will destroy the balance of MinMaxHeap
	 * that's why we need percolateUp Test for theElement
	 */
	percolateUp(theElement, index);
	
}
```

- deleteMax

    类似 evenDepthPercolateUp & oddDepthPercolateUp 的关系
    \
    deleteMax <--> deleteMin
    \
    findMaxIndex <--> findMinIndex
    \
    maxPercolateDown <--> minPercolateDown
    
    仅改变比较符号的方向

- buildHeap 

    1. 从头到尾把 array 各元素做上滤处理

```java
/*
 * percolateUp from top to tail
 * percolateDown ERROR
 */
 private void buildHeap() {
	for(int i = 1; i <= size; i++) {
		percolateUp(array[i], i);
	}
}
```
- merge

    1. otherHeap.array append -> this.array
    2. buildHeap

```java
public void merge(MinMaxHeap<AnyType> otherHeap) {
	if(this.size == 0) {
		array = otherHeap.array;
		return;
	} else if(otherHeap.size == 0) {
		return;
	}
	AnyType otherArray[] = otherHeap.array;
	int mergeSize = this.size + otherHeap.size;
	ensureCapacity((mergeSize+2)*11/10);
	for(int i = 0; i < otherHeap.size; i++) {
		array[++size] = otherArray[i+1];
	}
	buildHeap();
	otherHeap.clear();
}
```

- 整体实现见 [MinMaxHeap源码](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS6/src/MinMaxHeap.java)
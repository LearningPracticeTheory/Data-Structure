# MergeSort 归并排序

> 主要说明**优化的过程**，如讲解过程不明了，请见 [源码](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/src/MergeSort.java)
    
- 简介

    归并排序是将数组不断**二分**（divide）至单节点/双节点，**排序**后再逐一**合并**（merge）回原始数组的算法
    \
    其实现过程是==分治==（divide and conquer）的典例
    
- 概述

    1. sort0(); 主要是将数组长度分为**奇偶**的情况进行 divide，merge 两种情况通吃
    \
    相对 sort1 而言**数组拷贝的次数较少**
    
    2. sort1(); 通过**二分法**（binary divide）& **后序合并**（post-order merge）
    \
    相对 sort0 而言代码更简单直接，**奇偶情况合并处理**
    
    3. sort2(); 
    \
    结合 sort0 & sort1 的优势，主要优化的方面是**减少数组拷贝的次数**，减少内存消耗
    
- 说明

    AnyType 为 **non-static** 类型，而排序方法需要是 **static** 方法
    \
    所以引入泛型后，无法对 AnyType array[] 成员化
    \
    解决方案：**作为参数传递**
    
## sort0

- 算法实现

    1. divide 分**奇偶情况**以及**原数组长度为 1** 的特殊情况
    \
    奇数长度的情况，去除尾元素，剩余的偶数长度交由偶情况处理
    \
    各个 subarray 分至 `length == 1` 后开始 merge 操作
    
    2. merge 分 **单元素 + array 合并** & **array1 + array2 合并** 两种情况
    \
    通过比较两个（已排序）数组，将较小值逐一放置到数组中
    \
    实际上这是后期作为优化合并多种情况，从而简化代码
    
    3. merge 结果作为 subarray 的新对象，即通过**改变引用指向已排序数组**
    \
    merge 过程即 sort 过程，merge 与 divide 相反，subarray 长度逐渐增加直到原数组长度
    
- [ ] 举个例子

> 将下面数组通过 MergeSort.sort0(); 实现排序

5 | 8 | 7 | 13 | 14 | 4 | 16
---|---|---|---|---|---|---

> 首先左边分至单元素数组

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/merge0.png)

> 其次进行合并

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/merge1.png)

> 同理处理右边

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/merge2.png)

> 合并刚排序的左右边，以及最初分离的末尾元素

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/merge3.png)

- 代码实现

```java
/* 
 * divide until single node, then merge from deep to shallow,
 * which means the length of arrays increases with the merge
 * and the arrays are sorted when merge
 */
public static<AnyType extends Comparable<AnyType>> 
void sort0(AnyType array[]) {
	if(array.length <= 1) {
		return;
	}
	/*
	 * ERROR on use `array = divide(array)`; which only change the formal parameter
	 * technically only change formal parameter points to the new array[], 
	 * but actual parameter still points to original array[],
	 * which means it didn't change the actual parameter at all
	 */
	AnyType tmpArray[] = divide(array);
	for(int i = 0; i < tmpArray.length; i++) { //so change the elements of original array one by one
		array[i] = tmpArray[i]; //which are in heap
	}
}

/*
 * divide the array that we get
 */
@SuppressWarnings("unchecked")
private static<AnyType extends Comparable<AnyType>>
AnyType[] divide(AnyType array[]) { 
	
	/*
	 * odd divide -> evenArray + single element -> merge back into array
	 */
	if(array.length % 2 != 0 && array.length != 1) { //length >= 3
		AnyType tmpArray[] = (AnyType[]) new Comparable[array.length-1];
		for(int i = 0; i < tmpArray.length; i++) {
			tmpArray[i] = array[i];
		}
		tmpArray = divide(tmpArray);
		return merge(tmpArray, array[array.length-1]);
	}

	AnyType array1[] = (AnyType[]) new Comparable[array.length/2];
	AnyType array2[] = (AnyType[]) new Comparable[array.length/2];
	/*
	 * equally divide array into array1 & array2
	 */
	for(int i = 0; i < array1.length; i++) {
		array1[i] = array[i];
		array2[i] = array[i+array1.length];
	}
	
	if(array1.length != 1) {
		array1 = divide(array1);
		array2 = divide(array2);
	}
	
	return merge(array1, array2);
}

/*
 * merge array1 & array2 into array
 * case include evenMerge & oddMerge
 */
@SuppressWarnings("unchecked")
private static<AnyType extends Comparable<AnyType>>
AnyType[] merge(AnyType array1[], AnyType array2[]) {
	AnyType array[] = (AnyType[]) new Comparable[array1.length + array2.length];
	int index1 = 0;
	int index2 = 0;
	int index; //array.index
	for(index = 0; index < array.length; index++) {
		if(index1 == array1.length || index2 == array2.length) {
			break;
		}
		if(array1[index1].compareTo(array2[index2]) < 0) {
			array[index] = array1[index1++];
		} else {
			array[index] = array2[index2++];
		}
	}
	if(index1 == array1.length) {
		for(; index2 < array2.length; index2++) {
			array[index++] = array2[index2];
		}
	} else {
		for(; index1 < array1.length; index1++) {
			array[index++] = array1[index1];
		}
	}
	return array;
}

/*
 * merge a element & an array
 * of course treat theElement as an single node array
 */
@SuppressWarnings("unchecked")
private static<AnyType extends Comparable<AnyType>>
AnyType[] merge(AnyType[] tmpArray, AnyType theElement) { //odd only
	AnyType array[] = (AnyType[]) new Comparable[1];
	array[0] = theElement;
	return merge(array, tmpArray);
}
```

## sort1

- 算法实现

    1. **二分法 divide + 后序 merge**

    2. divide 至**双节点**数组时，开始 merge sort 操作（同样包含单节点情况）
    
    3. merge odd even 通吃，原数组 length == 1 也包含在内
    \
    **array --copy 所需位置元素--> tmpArray --sort 数据回馈--> array**
    \
    tmpArray 自始至终都是 array.length 的长度，难免在 **Heap** 中有过多 **NULL 对象**
    \
    **数组拷贝次数过多**，无形消耗内存，同时这也是 sort2 所优化的内容
    
    4. merge 都是**连续的元素**，所以只给定三个位置参数，leftEnd 由 rightStart 给出
    
- 举例说明

> divide 过程与 sort0 divide 相似，但顺序 & 分割节点数略有不同，详见图

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/divide1.png)

> 主要看 merge 过程，见图

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/sort1Merge.png)

**注意**: 图中节点为相对位置，即 divide 深度的简化图，而非实际数组下标

> 这里合并的过程类似**后序**的方式
\
> 拆分的最小数组含 **2 个元素**
\
> 当然也可能因为上一层的元素只有奇数个
\
> 而下层拆分时出现**单元素**的情况
\
> 这里奇偶情况通吃，是对 **sort0** 的改进简化

- 代码实现

```java
@SuppressWarnings("unchecked")
public static<AnyType extends Comparable<AnyType>> void sort1(AnyType array[]) {
	AnyType tmpArray[] = (AnyType[]) new Comparable[array.length];
	divide(array, tmpArray, 0, array.length); //[start, end);
}

private static<AnyType extends Comparable<AnyType>> 
void divide(AnyType array[], AnyType tmpArray[], int leftStart, int rightEnd) {
	/*
	 * binary divide + post-order merge
	 * @see BinarySearch in algorithm
	 */
	if(leftStart < rightEnd-1) {
		int center = (leftStart + rightEnd) / 2; //== leftStart + (rightEnd - leftStart) / 2;
		divide(array, tmpArray, leftStart, center);
		divide(array, tmpArray, center, rightEnd);
		merge(array, tmpArray, leftStart, center, rightEnd);
	}
}

private static<AnyType extends Comparable<AnyType>> 
void merge(AnyType[] array, AnyType[] tmpArray, int leftStart, int rightStart, int rightEnd) {
	int leftEnd = rightStart;
	int index = leftStart;
	int numOfElements = rightEnd-leftStart;

	while(leftStart < leftEnd && rightStart < rightEnd) {
		if(array[leftStart].compareTo(array[rightStart]) < 0) {
			tmpArray[index++] = array[leftStart++];
		} else {
			tmpArray[index++] = array[rightStart++];
		}
	}
	
	while(leftStart < leftEnd) {
		tmpArray[index++] = array[leftStart++];
	}
	
	while(rightStart < rightEnd) {
		tmpArray[index++] = array[rightStart++];
	}
	
	for(int i = 0; i < numOfElements; i++) {
		array[--rightEnd] = tmpArray[rightEnd];
	}
	
}
```

- 对比 sort0 & sort1 **divide** 过程

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/divide01.png)

> 可见拆分的**顺序以及节点数量**并不相同
\
> 根据对比可知，sort0 拆分最小单元为**单节点**，sort1 拆分最小单元为**双节点**

- 对比 sort0 & sort1 **merge** 过程

![image](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/pic/sort0sort1.png)

> 可见合并的**顺序以及节点数量**并不相同，实际上合并的**次数**也不相同

## sort2

- 结合 sort0 & sort1 的优点

    1. 通过返回已排序数组，++改变原数组的指向++，**减少数组拷贝**
    
    2. ++二分法拆分 + 后序合++并，简化代码，**合并奇偶处理**
    
> 由于代码重复度高，所以在此不作详细解释，详细见 [MergeSort](https://github.com/LearningPracticeTheory/Data_Structure/blob/master/JDS7/src/MergeSort.java)
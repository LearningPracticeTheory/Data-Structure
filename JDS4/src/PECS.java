
public class PECS { //Producer Extends, Consumer Super
	public static void main(String args[]) {
		
Fruit fruit = new Apple(); //"Apple IS-A Fruit" 
Plate<Fruit> fruitPlate = new Plate<Apple>(new Apple()); //"Plate<Apple> IS-NOT-A Plate<Fruit>"
		/*
		"装 Apple 的盘子无法转换成 装 Fruit 的盘子"
		"容器里面的东西有 extends，但容器没有 extends"
		*/
//List<Object> list1 = new ArrayList<Integer>(); //参照实例
		/*
		- Type mismatch: cannot convert from Plate<Apple> to Plate<Fruit>
		*/
/*? 通配符*/
Plate<?> p = new Plate<Fruit>(new GreenApple()); // "p 不知道盘子里面装什么"

		Object obj1 = p.get(); //"祖宗类" //correct
		Fruit f1 = p.get(); //"拿到盘子里面的东西不知道是什么" //error 
		p.set(new Apple()); //"不知道盘子里面的东西是什么" //error
/*extends 规定上限*/
Plate<? extends Fruit> fpe = new Plate<Apple>(new Apple()); 
		/*
		"编译器将 Plate<Apple> 当作 CAP#1 占位符看待"
		"用来捕获 Fruit or Fruit子类，不知道具体类，无法 set"
		*/
		fpe.set(new Apple()); //error
		fpe.set(new Fruit()); //error
		/*
		The method set(capture#1-of ? extends Fruit) 
		in the type Plate<capture#1-of ? extends Fruit> 
		is not applicable for the arguments (Apple)
		*/
		Fruit ff = fpe.get(); //"读取作为 Fruit | Fruit 的基类" //correct
		Food fo = fpe.get(); //"父类引用指向子类对象"//correct
		Apple ap = fpe.get(); //"Fruit 盘子并不知道具体是什么水果，但是知道是一类  Fruit" //error
/*super 规定下限*/ 
Plate<? super Fruit> fps = new Plate<Apple>(new Apple());
Plate<? super Fruit> fps1 = new Plate<Fruit>(new Apple());
		/*
		"编译器将 Plate<Fruit> 当作 CAP#1 占位符"
		"fps1 容器里只知道装了 Fruit | Fruit 的基类"
		*/
Plate<? super Fruit> fps2 = new Plate<Food>(new Apple()); 
Plate<? super Fruit> fps3 = new Plate<Food>(new Fruit());

		Apple app = fps2.get(); //"获取的是 Fruit | Fruit 的基类类型，并不知道具体是那种类型"
		Food fd = fps2.get();
		/*
		Type mismatch: cannot convert from <capture#6-of ? super Fruit> to Food
		*/		
		Object obj = fps2.get();
		if(fps2.get() instanceof Food) { 
			 Food f = (Food)fps2.get();
		}

		fps1.set(new Apple()); //"Apple 当作一种 Fruit | Fruit 的基类对待" //correct
		fps1.set(new Fruit()); //"父类引用指向子类对象"//correct

	}
}

class Plate<T> {
	T item;
	Plate(T item) {this.item = item;}
	public void set(T t) {item = t;}
	public T get() {return item;}
}

class Food {}

class Fruit extends Food {}
class Meat extends Food {}

class Apple extends Fruit {}

class GreenApple extends Apple {}
class RedApple extends Apple {}

/*

		//\	   //\
	   //\/	  //\/
	  /////////\/
	 //\/\\\//\/
	//\/   //\/
	\\/	   \\/

*/

/*

		 //\
	    //\/
	   //\/
	  //\/
	 //\/
	//\/
   //\/
   \\/
	
*/

/*

	   ///////\
	   \\//\\\/
	    //\/
	   //\/
	  //\/
     //\/
  ///////\
  \\\\\\\/

*/

/*

	///////////\
	\\\\//\\\\\/
	   //\/
	  //\/
	 //\/
	 \\/

*/

/*

	   ////////\
      //\\\\//\/
     //\/  //\/
    //\/  //\/
   ////////\/
   \\\\\\\\/
  
*/

/*

	  	//////////\
       //\\\\\\\\\/
      //\/
     //\/
    //\/
   //\/ 
  //////////\
  \\\\\\\\\\/

*/

/*

		//////////\
	   //\\\\\\\\\/
	  //\/
     //////////\
	//\\\\\\\\\/
   //\/
  //////////\
  \\\\\\\\\\/

*/

/*

		//////////\
	   //\\\\\\\\\/
	  //\/
	 //////////\
	//\\\\\\\\\/
   //\/
  //\/
  \\/

*/

/*

		//////////\
	   //\\\\\\//\/
	  //\/    //\/
	 //////////\/
	//\\\\\\//\/
   //\/	   //\/
  //////////\/
  \\\\\\\\\\/
  
*/

/*

		//\
	   //\/
	  //\/    
	 //////////\
	//\\\\\\//\/
   //\/	   //\/
  //////////\/
  \\\\\\\\\\/

*/

/*

	  	//////////\
       //\\\\\\//\/
      //\/    //\/
     //\/    //\/
    //\/	//\/
   //\/    //\/
  //////////\/
  \\\\\\\\\\/

*/

/*

	  	//////////\
       //\\\\\\\\/\\
      //\/      /\/\
     //\/      //\\/
    //\/	  ///\/
   //\/      //\\/
  //////////\/\//
  \\\\\\\\\\\//


/*

/*

		//////////\
	   //\\\\\\\\\/
	  //\/       
     //\/ /////\
	//\/  \\//\/
   //\/    //\/
  //////////\/
  \\\\\\\\\\/

*/

/*

		//\
	   //\/
      //\/
	 /////////\
	//\\\\\//\/
   //\/	  //\/
  //\/   //\/
  \\/    \\/

*/

/*

		//\	    //\
	   //\/	   //\/	
	  //\/	  //\/
	 //////////\/
	//\\\\\\//\/
   //\/	   //\/
  //\/    //\/
  \\/     \\/

*/

/*

	   ///\
	   \\\/	
	  	 
	 //\
	//\/
   //\/
  //\/
  \\/   

*/

/*

		 /////////\
		 \\\//\\\\/
		   //\/
		  //\/
		 //\/
	    //\/
    \////\/
     \\\\/	
     
*/

/*

		//\    //\
	   //\/  //^\/
	  //\/ //^\/
	 //////^/
	//\\\\\/
   //\/ \\'\
  //\/	 \\'\
  \\/     \\\\
  
*/

/*

		//\
	   //\/
	  //\/       
     //\/ 
	//\/  
   //\/    
  //////////\
  \\\\\\\\\\/

*/

/*

		//////////\
	   //\\\\\\//\/
	  //\/    //\/
	 //////////\/
	//\\\\\\\\\/
   //\/	   
  //\/
  \\/

*/

/*

		//////////\
	   //\\\\\\\\\/
	  //\/    
	 //////////\
	 \\\\\\\//\/
    	   //\/
  //////////\/
  \\\\\\\\\\/

*/

/*

	  	//\		//\
       //\/	   //\/
      //\/    //\/
     //\/    //\/
    //\/	//\/
   //\/    //\/
  //////////\/
  \\\\\\\\\\/

*/

/*

	 //////////\
	 \\\\\\\//\/
	     //\//      
       //\// 
	 //\//  
   //\//    
  //////////\
  \\\\\\\\\\/

*/

/*

		//\	   			///\
	   //\/	   		    \\\/	
      //\/						
	 /////////\	 	  //\
	//\\\\\//\/		 //\/
   //\/	  //\/  	//\/
  //\/   //\/  	   //\/
  \\/    \\/   	   \\/   
  
*/

/*

		//\	    //\		  ///////\
	   //\/	   //\/		  \\//\\\/
	  //\/	  //\/	       //\/
	 //////////\/		  //\/
	//\\\\\\//\/		 //\/
   //\/	   //\/			//\/
  //\/    //\/		 ///////\
  \\/     \\/		 \\\\\\\/

*/

/*

		//\	    //\		  //////////\		//\			    //\			   //////////\
	   //\/	   //\/		 //\\\\\\\\\/	   //\/		       //\/			  //\\\\\\//\/
	  //\/	  //\/	    //\/   			  //\/		      //\/			 //\/    //\/
	 //////////\/	   //////////\  	 //\/		     //\/		    //\/    //\/
	//\\\\\\//\/	  //\\\\\\\\\/		//\/		    //\/		   //\/    //\/
   //\/	   //\/		 //\/			   //\/		       //\/			  //\/    //\/
  //\/    //\/		//////////\		  //////////\	  //////////\	 //////////\/
  \\/     \\/	    \\\\\\\\\\/		  \\\\\\\\\\/     \\\\\\\\\\/  	 \\\\\\\\\\/

*/

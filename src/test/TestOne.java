package test;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//create by songdewei 20161029
public class TestOne {
	/*
	 * Java 反射API的第一个主要作用是获取程序在运行时刻的内部结构。这对于程序的检查工具和调试器来说，是非常实用的功能。只需要短短的十几行代码，
	 * 就可以遍历出来一个Java类的内部结构，包括其中的构造方法、声明的域和定义的方法等。这不得不说是一个很强大的能力。只要有了java.lang.
	 * Class类
	 * 的对象，就可以通过其中的方法来获取到该类中的构造方法、域和方法。对应的方法分别是getConstructor、getField和getMethod
	 * 。这三个方法还有相应的getDeclaredXXX版本，区别在于getDeclaredXXX版本的方法只会获取该类自身所声明的元素，
	 * 而不会考虑继承下来的。Constructor、Field和Method这三个类分别表示类中的构造方法、域和方法。
	 * 这些类中的方法可以获取到所对应结构的元数据。
	 * 
	 * 反射API的另外一个作用是在运行时刻对一个Java对象进行操作。
	 * 这些操作包括动态创建一个Java类的对象，获取某个域的值以及调用某个方法。在Java源代码中编写的对类和对象的操作，
	 * 都可以在运行时刻通过反射API来实现。
	 */
	public void test() {
		SimpleModel myClass = new SimpleModel(2); // 一般做法
		myClass.increase(2);
		System.out.println("Normal -> " + myClass.count);
		try {
			Constructor constructor = SimpleModel.class.getConstructor(int.class); // 获取构造方法
			SimpleModel myClassReflect = (SimpleModel) constructor.newInstance(10); // 创建对象
			Method method = SimpleModel.class.getMethod("increase", int.class); // 获取方法
			method.invoke(myClassReflect, 5); // 调用方法
			Field field = SimpleModel.class.getField("count"); // 获取域
			System.out.println("Reflect -> " + field.getInt(myClassReflect)); // 获取域的值
		} catch (Exception e) {
			e.printStackTrace();
		}
		//由于数组的特殊性，Array类提供了一系列的静态方法用来创建数组和对数组中的元素进行访问和操作。
		// 使用Java反射API的时候可以绕过Java默认的访问控制检查，
		//比如可以直接获取到对象的私有域的值或是调用私有方法。
		//只需要在获取到Constructor、Field和Method类的对象之后，调用setAccessible方法并设为true即可。有了这种机制，就可以很方便的在运行时刻获取到程序的内部状态。
		Object array = Array.newInstance(String.class, 10); //等价于 new String[10]
		Array.set(array, 0, "Hello");  //等价于array[0] = "Hello"
		Array.set(array, 1, "World");  //等价于array[1] = "World"
		System.out.println(Array.get(array, 0));  //等价于array[0]
	}

	public static void main(String[] args) {
		TestOne main = new TestOne();
		main.test();
	}
}

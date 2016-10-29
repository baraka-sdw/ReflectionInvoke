package test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestTwo {
	/*
	 * Java 5中引入了泛型的概念之后，Java反射API也做了相应的修改，以提供对泛型的支持。由于类型擦除机制的存在，泛型类中的类型参数等信息，
	 * 在运行时刻是不存在的。JVM看到的都是原始类型。对此，Java
	 * 5对Java类文件的格式做了修订，添加了Signature属性，用来包含不在JVM类型系统中的类型信息。比如以java.util.List接口为例
	 * ，在其类文件中的Signature属性的声明是<E:Ljava/lang/Object;>Ljava/lang/Object;Ljava/util
	 * /Collection<TE;>;;
	 * ，这就说明List接口有一个类型参数E。在运行时刻，JVM会读取Signature属性的内容并提供给反射API来使用。
	 * 
	 * 比如在代码中声明了一个域是List<String>类型的，虽然在运行时刻其类型会变成原始类型List，
	 * 但是仍然可以通过反射来获取到所用的实际的类型参数。
	 */
	public void test() throws NoSuchFieldException, SecurityException {
		Field field = Pair.class.getDeclaredField("decimal"); // myList的类型是List
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			Type[] actualTypes = paramType.getActualTypeArguments();
			for (Type aType : actualTypes) {
				if (aType instanceof Class) {
					Class clz = (Class) aType;
					System.out.println(clz.getName()); // 输出java.lang.String
				}
			}
		}
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		TestTwo main = new TestTwo();
		main.test();
	}
}

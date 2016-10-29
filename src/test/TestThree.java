package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TestThree {
	public void test() {
	}

	/**
	 * 动态代理
	 * 
	 * 熟悉设计模式的人对于代理模式可 能都不陌生。
	 * 代理对象和被代理对象一般实现相同的接口，调用者与代理对象进行交互。代理的存在对于调用者来说是透明的，调用者看到的只是接口。代理对象则可以封装一些内部的处理逻辑，如访问控制、远程通信、日志、缓存等。比如一个对象访问代理就可以在普通的访问机制之上添加缓存的支持。这种模式在RMI和EJB中都得到了广泛的使用。传统的代理模式的实现，需要在源代码中添加一些附加的类。这些类一般是手写或是通过工具来自动生成。JDK
	 * 5引入的动态代理机制，允许开发人员在运行时刻动态的创建出代理类及其对象。在运行时刻，可以动态创建出一个实现了多个接口的代理类。每个代理类的对象都会关联一个表示内部处理逻辑的InvocationHandler接
	 * 口的实现。当使用者调用了代理对象所代理的接口中的方法的时候，这个调用的信息会被传递给InvocationHandler的invoke方法。在
	 * invoke方法的参数中可以获取到代理对象、方法对应的Method对象和调用的实际参数。invoke方法的返回值被返回给使用者。这种做法实际上相
	 * 当于对方法调用进行了拦截。熟悉AOP的人对这种使用模式应该不陌生。但是这种方式不需要依赖AspectJ等AOP框架。
	 * 
	 * 下面的代码用来代理一个实现了List接口的对象。所实现的功能也非常简单，那就是禁止使用List接口中的add方法。如果在getList中传入一个实现List接口的对象，那么返回的实际就是一个代理对象，尝试在该对象上调用add方法就会抛出来异常。
	 **/
	public List getList(final List list) {
		return (List) Proxy.newProxyInstance(List.class.getClassLoader(), new Class[] { List.class },
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("add".equals(method.getName())) {
							throw new UnsupportedOperationException();
						} else {
							return method.invoke(list, args);
						}
					}
				});
	}
	public static void main(String[] args) {
		TestThree main =new TestThree(); 
		List<String> myList =new ArrayList<String>();
		myList.add("test");
		List<String> list=main.getList(myList);
		list.add("test");
	}
}

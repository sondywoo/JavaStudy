package com.designPattern.decorator;

/**
 * 设计模式 -- Decorator 装饰模式
 * 
 *  - 别名：Wrapper包装器
 *  - 动态地给某一个对象(Ex. 由ConcreteComponent实例化的对象)添加一些额外的职责(额外的职责在ConcreteDecorator1 & ConcreteDecorator2中)
 *  - 装饰模式是将组件嵌入另一个对象中，为这个对象添加职责。这个嵌入的对象称为“装饰”，这个装饰与它所装饰的组件ConcreteComponent接口一致，所以它
 *  		对使用该组件的客户是透明的，它将客户请求转发给该组件，并且可能在转发前后执行一些额外的动作
 *  - 装饰模式的透明性使得你可以递归地嵌套多个装饰，而此时对于使用它的用户来说并不清楚你有没有为它嵌套装饰，嵌套了几层装饰
 *  - 注意这是给某个对象而不是整个类添加一些功能, 若是对整个类扩展功能可以用继承机制或其它模式
 *  - 比较：	装饰模式可在运行时刻增加和删除职责，提供更加灵活的向对象添加职责的方式
 *  		继承机制要求为每个添加的职责创建一个新的子类，这会产生许多新的类，增加系统的复杂度
 *  		装饰模式可以很容易地重复添加一个特性，即将添加的职责重复一次
 *  		继承机制则要2次继承才行，而多次重复继承很容易出错
 *  - Decorator模式提供了一种“即用即付”的方法来添加职责，这样你就可以定义一个简单的类ConcreteComponent，然后用Decorator类给它逐渐地添加功能，
 *  		可以从简单的部件组合出复杂的功能，这样，应用程序不必为不需要的特征付出代价
 *  - 缺点：	Decorator模式往往会产生许多看上去类似的小对象，这些对象仅仅在它们相互连接的方式上有所不同，而不是它们的类或属性值不同。尽管对于那些
 *  		了解这些系统的人来说，很容易对它们进行定制，但是很难学习这些系统，很难排错。
 *  - 相关模式：
 *  	Decorator模式仅改变对象的职责而不改变它的接口；Adapter模式则将给对象一个全新的接口，即改变了它的接口
 *  	Decorator模式可看成一个退化的，仅有一个组件的组合(Composite模式)，而Decorator目的不在于对象聚集，仅给对象添加额外的职责
 * 
 * @author Sondy Woo
 */
public class TestDecorator {
	/**
	 * Client 客户端
	 *  - 调用经过装饰的对象，该对象中，Decorator将Client请求转发给它的Component对象，并有可能在转发请求前后执行一些附加的动作
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TestDecorator testDecorator = new TestDecorator();
		
		Component component = new ConcreteComponent();
		
		//	使用装饰接口1 ConcreteDecorator1 来装饰 Component
		Decorator decorator1 = new ConcreteDecorator1(component);
		testDecorator.callComponent(decorator1);

		//	使用装饰接口2 ConcreteDecorator2 来装饰 Component
		System.out.println();
		Decorator decorator2 = new ConcreteDecorator2(component);
		testDecorator.callComponent(decorator2);

		//	使用多重装饰接口来装饰 Component
		System.out.println();
		Decorator decorator3 = new ConcreteDecorator1(new ConcreteDecorator2(component));
		testDecorator.callComponent(decorator3);
		
		System.out.println();
		testDecorator.callComponent(new ConcreteDecorator1(decorator2));
	}
	public void callComponent(Component component){
		component.operation();
	}
}

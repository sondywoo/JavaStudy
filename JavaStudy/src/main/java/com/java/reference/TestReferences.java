package com.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Assert;

/**
 * Java中一共有4种类型的引用:
 * 		StrongReference
 * 		SoftReference
 * 		WeakReference
 * 		PhantomReference (幽灵引用)
 * 这 4种类型的引用与Java GC有着密切的关系
 * 
 * 
 * 级别		什么时候被垃圾回收   	用途      			生存时间
 * 强引用	从来不会            		对象的一般状态     JVM停止运行时终止
 * 软引用	在内存不足时      		对象简单？缓存     内存不足时终止
 * 弱引用	在垃圾回收时      		对象缓存    		GC运行后终止
 * 幽灵引用	Unknown     		Unknown     	Unknown
 * 
 * 
 * @author WUSO001
 */
public class TestReferences {

	public static void main(String[] args) throws InterruptedException {
		TestReferences obj = new TestReferences();
//		obj.strongReference();
//		obj.softReference();
//		obj.weakReference();
//		obj.phantomReference();
		
		/**
		 * PhantomReference有两个好处， 
		 * 
		 * 其一， 它可以让我们准确地知道对象何时被从内存中删除， 这个特性可以被用于一些特殊的需求中
		 * (例如 Distributed GC，XWork 和 google-guice 中也使用 PhantomReference 做了一些清理性工作).
		 * 
		 * 其二， 它可以避免 finalization 带来的一些根本性问题, 上文提到 PhantomReference 的唯一作用就是
		 * 跟踪 referent 何时被 enqueue 到 ReferenceQueue 中,但是 WeakReference 也有对应的功能, 两者的区别到底在哪呢 ?
		 * 这就要说到 Object 的 finalize 方法, 此方法将在 gc 执行前被调用, 如果某个对象重载了 finalize 方法
		 * 并故意在方法内创建本身的强引用,这将导致这一轮的 GC 无法回收这个对象并有可能引起任意次 GC， 
		 * 最后的结果就是明明 JVM 内有很多 Garbage 却 OutOfMemory， 使用 PhantomReference 就可以避免这个问题， 
		 * 因为 PhantomReference 是在 finalize 方法执行后回收的，也就意味着此时已经不可能拿到原来的引用，也就不会出现上述问题。
		 */
		
		
		obj.referenceQueue();
		
//		obj.weakHashMap();
		
	}
	
	/**
	 * StrongReference 是 Java 的默认引用实现,它会尽可能长时间的存活于 JVM 内， 
	 * 当没有任何对象指向它时，Java GC 执行后将会被回收
	 */
	public void strongReference() {   
		System.out.println("TestReferences,strongReference,START");
		Object referent = new Object();   
		/**  
		 * 通过赋值创建 StrongReference   
		 */  
		Object strongReference = referent;   
		Assert.assertSame(referent, strongReference);   
		referent = null;   
		System.gc();   
		/**  
		 * StrongReference 在 GC 后不会被回收  
		 * so when??
		 */  
		Assert.assertNotNull(strongReference);   
		System.out.println("TestReferences,strongReference,END");
	}
	
	
	/**
	 * SoftReference 于 WeakReference 的特性基本一致， 
	 * 最大的区别在于 SoftReference 会尽可能长的保留引用直到 JVM 
	 * Java GC 后 不会马上回收，只有内存不足时才会被回收(虚拟机保证), 
	 * 这一特性使得 SoftReference 非常适合缓存应用
	 */
	public void softReference() {   
		System.out.println("TestReferences,softReference,START");
		Object referent = new Object();   
		SoftReference<Object> softRerference = new SoftReference<Object>(referent);   

		/*
		 * softRerference.get() 返回对象的强引用，如果被GC回收了则返回NULL。
		 */
		Assert.assertNotNull(softRerference.get());   

		Assert.assertSame(referent, softRerference.get());

		referent = null;   
		System.gc();   

		/**  
		 *soft references 只有在 jvm OutOfMemory 之前才会被回收, 所以它非常适合缓存应用  
		 */  
		Assert.assertNotNull(softRerference.get());   
		System.out.println("TestReferences,softReference,END");
	}

	
	/**
	 * WeakReference是一个弱引用,
	 * 当所引用的对象在 JVM 内不再有强引用时, Java GC 后 weak reference 将会被自动回收
	 */
	public void weakReference() {   
		System.out.println("TestReferences,weakReference,START");
		Object referent = new Object();   
		WeakReference<Object> weakRerference = new WeakReference<Object>(referent);   
		Assert.assertSame(referent, weakRerference.get());   
		referent = null;   
		System.gc();   
		/**  
		 * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收  
		 */  
		Assert.assertNull(weakRerference.get());   
		System.out.println("TestReferences,weakReference,END");
	}
	
	
	/**
	 * Phantom Reference(幽灵引用) 与 WeakReference 和 SoftReference 有很大的不同,
	 * 因为它的 get() 方法永远返回 null, 这也正是它名字的由来
	 * 
	 * 注意构造 PhantomReference 时的第二个参数 ReferenceQueue(事实上 WeakReference & SoftReference 也可以有这个参数)，
	 * PhantomReference 唯一的用处就是跟踪 referent何时被 enqueue 到 ReferenceQueue 中.
	 */
	public void phantomReference() {   
		System.out.println("TestReferences,phantomReference,START");
		Object referent = new Object();   
		PhantomReference<Object> phantomReference = new PhantomReference<Object>(referent, new ReferenceQueue<Object>());   

		/**  
		 * phantom reference 的 get 方法永远返回 null   
		 */  
		Assert.assertNull(phantomReference.get());   
		System.out.println("TestReferences,phantomReference,END");
	}
	
	
	
	/**
	 * 当一个 WeakReference 开始返回 null 时， 它所指向的对象已经准备被回收， 这时可以做一些合适的清理工作. 
	 * 将一个 ReferenceQueue 传给一个 Reference 的构造函数， 当对象被回收时， 虚拟机会自动将这个对象插入到 ReferenceQueue 中， 
	 * WeakHashMap 就是利用 ReferenceQueue 来清除 key 已经没有强引用的 entries.
	 * @throws InterruptedException
	 */
	public void referenceQueue() throws InterruptedException {  
		System.out.println("TestReferences,referenceQueue,START"); 
		Object referent = new Object();  
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();   
		WeakReference<Object> weakReference = new WeakReference<Object>(referent, referenceQueue);   

		Assert.assertFalse(weakReference.isEnqueued());   
		Reference<? extends Object> polled = referenceQueue.poll();   
		Assert.assertNull(polled);   

//		System.out.println("TestReferences,referenceQueue,1"); 
//		Reference<? extends Object> removed2 = referenceQueue.remove();   //blocking here...
//		System.out.println("TestReferences,referenceQueue,2"); 
//		Assert.assertNotNull(removed2);
//		System.out.println("TestReferences,referenceQueue,3"); 

		referent = null;   
		System.gc();   

		Assert.assertTrue(weakReference.isEnqueued());   
		Reference<? extends Object> polled2 = referenceQueue.poll();   
		Assert.assertNull(polled2);   
		
		Reference<? extends Object> removed = referenceQueue.remove();   
		Assert.assertNotNull(removed);  
		System.out.println("TestReferences,referenceQueue,END"); 
	}
	
	
	
	/**
	 * WeakHashMap 使用 WeakReference 作为 key， 
	 * 一旦没有指向 key 的强引用, WeakHashMap 在Java GC 后将自动删除相关的 entry
	 * @throws InterruptedException
	 */
	public void weakHashMap() throws InterruptedException {   
		Map<Object, Object> weakHashMap = new WeakHashMap<Object, Object>();   
		Object key = new Object();   
		Object value = new Object();   
		weakHashMap.put(key, value);   

		Assert.assertTrue(weakHashMap.containsValue(value));   

		key = null;   
		System.gc();   

		/**  
		 * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理  
		 */  
		Thread.sleep(1000);   

		/**  
		 * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry  
		 */  
		Assert.assertFalse(weakHashMap.containsValue(value));   
	}
}

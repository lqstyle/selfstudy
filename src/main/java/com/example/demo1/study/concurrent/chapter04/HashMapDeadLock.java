package com.example.demo1.study.concurrent.chapter04;

import java.util.HashMap;
import java.util.Map;

/**
 * 那么什么时候回产生扩容呢？
 * （1）初始化HashMap时，第一次进行put操作
 * （2）当键值对的个数大于threshold阀值时产生扩容，threshold=size*loadFactor
 * 上面就是HashMap扩容的源代码，我已经加上了注释，相信大家都能看懂了。总结一下，HaspMap扩容就是就是先计算
 * 新的hash表容量和新的容量阀值，然后初始化一个新的hash表，将旧的键值对重新映射在新的hash表里。这里实现的细节当然
 * 没有我说的那么简单，如果在旧的hash表里涉及到红黑树，那么在映射到新的hash表中还涉及到红黑树的拆分。
 * 在扩容的源代码中作者有一个使用很巧妙的地方，是键值对分布更均匀，不知道读者是否有看出来。在遍历原hash桶时的
 * 一个链表时，因为扩容后长度为原hash表的2倍，假设把扩容后的hash表分为两半，分为低位和高位，如果能把原链表的键值对，
 * 一半放在低位，一半放在高位，这样的索引效率是最高的。那看看源码里是怎样写的。大师通过e.hash & oldCap == 0来判断，
 * 这和e.hash & (oldCap - 1) 有什么区别呢。下面我通过画图来解释一下。
 *
 * 作者：zuckerbergJu2.0
 * 链接：https://juejin.cn/post/6844903559675248647
 * 来源：掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class HashMapDeadLock {

  private final Map<String, String> MAP = new HashMap<>();


  private void add(String key, String value) {
    MAP.put(key, value);
    System.out.println("当前线程为"+Thread.currentThread());
  }


  public static void main(String[] args) {
    HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
    for (int i = 0; i < 2; i++) {
      new Thread(() -> {
        for (int j = 0; j < Integer.MAX_VALUE; j++) {
          hashMapDeadLock.add(String.valueOf(j), String.valueOf(j));
        }
      }, String.valueOf(i)).start();
    }

  }
}

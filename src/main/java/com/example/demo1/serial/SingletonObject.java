package com.example.demo1.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
1、新增序列化类时，建议新增一个自定义id，防止后续版本之间不兼容
2、static、transient修饰的字段不会被序列化，序列化同样会序列化private属性
3、序列化可以实现深度克隆
4、实现序列化有两种方式，一种是直接实现Serializable接口，另一种是实现Externalizable接口，后者允许我们自定义序列化规则

作者：拥抱心中的梦想
链接：https://juejin.im/post/6844904037268062222
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SingletonObject implements Serializable {

  private static final SingletonObject INSTANCE = new SingletonObject();

  private static SingletonObject getInstance() {
    return INSTANCE;
  }

  private SingletonObject() {
  }

  public static Object deepCopy(Object obj) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      new ObjectOutputStream(bos).writeObject(obj);
      ByteArrayInputStream bin =
          new ByteArrayInputStream(bos.toByteArray());
      return new ObjectInputStream(bin).readObject();
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static void main(String[] args) {
    SingletonObject singletonObject = (SingletonObject) deepCopy(SingletonObject.getInstance());
    System.out.println(singletonObject == SingletonObject.getInstance());
  }

  /*
  若实现了readResolve方法，则序列化返回次对象，否未实现，则返回新对象
   */
  protected final Object readResolve() throws NotSerializableException {
    return INSTANCE;
  }

}

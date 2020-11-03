package com.example.demo1.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MySerialize implements Serializable {

  private String name;
  private int age;

  public MySerialize(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(
        new FileOutputStream(new File("C:\\Users\\liangqing\\Desktop\\1.txt")));
    objectOutputStream.writeObject(new MySerialize("lq", 122));

    ObjectInputStream inputStream = new ObjectInputStream(
        new FileInputStream("C:\\Users\\liangqing\\Desktop\\1.txt"));
    Object o = inputStream.readObject();
    System.out.println(o.toString());
  }

  @Override
  public String toString() {
    return "MySerialize{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }

  private void readObject(java.io.ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    ObjectInputStream.GetField fields = s.readFields();
    int age = fields.get("age", 0);
    if (age > 100 || age < 0) {
      throw new InvalidObjectException("age范围不对!");
    }
    this.age = age;
  }

}

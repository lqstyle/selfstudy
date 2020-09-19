package com.example.demo1.algorithm.order;

public class Bubble {

  public static void main(String[] args) {
    int[] array  = new int[]{2,1,4,8,5,6,9,7};

    int a;
    for (int i = 0; i <array.length-1 ; i++) {
      for (int j = 0; j <array.length-1-i ; j++) {
          if(array[j]>array[j+1]){
            a=array[j];
            array[j] =array[j+1];
            array[j+1] = a;
          }
      }
    }
    for (int i = 0; i < array.length; i++) {
      System.out.println(i);
    }
  }

}

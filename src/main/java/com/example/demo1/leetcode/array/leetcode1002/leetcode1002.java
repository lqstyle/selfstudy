package com.example.demo1.leetcode.array.leetcode1002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

你可以按任意顺序返回答案。

示例 1：

输入：["bella","label","roller"]
输出：["e","l","l"]
示例 2：

输入：["cool","lock","cook"]
输出：["c","o"]
 

提示：

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] 是小写字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-common-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class leetcode1002 {

  public List<String> commonChars(String[] A) {
    List<Character> list =null;

    String s;
    int j;
    char[] c;

    List<Character> characters = new ArrayList<>();
    char[] chars = A[0].toCharArray();
    for(int k=0;k<chars.length;k++){
      characters.add(chars[k]);
    }
    List<String> ss = new ArrayList<>();
    for(int i=1;i<A.length;i++){
         s = A[i];
         j =s.length();
         c= s.toCharArray();
         list =  new ArrayList<>();
         for(int k=0;k<c.length;k++){
           if(characters.contains(c[k])){

           }
         }
    }

    return null;
  }
}

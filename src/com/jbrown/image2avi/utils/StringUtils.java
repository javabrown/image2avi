package com.jbrown.image2avi.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils {
  public static String[] buildStrMatrixWithReplacementCharSet(String str,
      char[][] charRelacementMatrix) {
    List<String[]> resultsList = new ArrayList<String[]>();

    for (char[] set : charRelacementMatrix) {
      if (set == null || set.length == 0 || set.length != 2) {
        continue;
      }

      String[] mixStrs = StringUtils.relpaceOneByOne(str, set[0], set[1]);

      if (mixStrs != null && mixStrs.length > 0) {
        resultsList.add(mixStrs);

        for (String mix : mixStrs) {
          for (char[] set0 : charRelacementMatrix) {
            String[] mixStrs0 = StringUtils.relpaceOneByOne(mix, set0[0],
                set0[1]);
            resultsList.add(mixStrs0);
          }
        }
      }
    }

    Set<String> set = new HashSet<String>();

    for (String[] result : resultsList) {
      for (String s : result) {
        set.add(s);
      }
    }

    return set.toArray(new String[0]);
  }

  public static String replaceCharAt0(String s, int pos, char c) {
    return s.substring(0, pos) + c + s.substring(pos + 1);
  }

  public static String replaceCharAt(String s, int pos, char c) {
    return s.substring(0, pos) + c + s.substring(pos + 1);
  }

  public static String[] relpaceOneByOne(String str, char from, char to) {
    List<String> list = new ArrayList<String>();

    list.add(str);
    if (str != null && str.trim().length() > 0) {
      Integer[] indexs = indexOf(str, from);
      String paramStr = str;

      while (str.indexOf((int) from) >= 0) {
        // str = replaceCharAt(str, str.indexOf((int)from), to);
        for (int i : indexs) {
          // str = replaceCharAt(repStr, i, to);
          list.add(replaceCharAt(paramStr, i, to));
        }

        str = replaceCharAt(str, str.indexOf((int) from), to);
        list.add(new String(str));
      }

      list.add(paramStr);
    }

    return list.toArray(new String[0]);
  }

  public static Integer[] indexOf(String str, char c) {
    List<Integer> list = new ArrayList<Integer>();

    if (str != null && str.trim().length() > 0) {
      while (str.indexOf((int) c) >= 0) {
        int lookupIndex = str.indexOf((int) c);
        str = replaceCharAt0(str, lookupIndex, ' ');
        // System.out.println(str);
        list.add(lookupIndex);
      }
    }

    return list.toArray(new Integer[0]);
  }

  public static boolean isEmpty(String s) {
    return (s == null) || s.trim().equals("");
  }

  public static boolean isEmpty(String... stringArray) {
    if(stringArray == null || stringArray.length == 0){
      return true;
    }

    for (String s : stringArray) {
      if (isEmpty(s)) {
        return true;
      }
    }


    return false;
  }

  public static boolean compare(String stringToCompare, String... possibleVals) {
    if (isEmpty(stringToCompare) || possibleVals == null) {
      return false;
    }

    for (String s : possibleVals) {
      if (stringToCompare.equalsIgnoreCase(avoidNullString(s))) {
        return true;
      }
    }

    return false;
  }

  public static String avoidNullString(String str) {
    if (isEmpty(str)) {
      return "";
    }

    return str;
  }

  public static boolean isEquals(String... stringArray) {
    String s1 = null;

    for (String s : stringArray) {
      if (isEmpty(s)) {
        return false;
      }

      if (s1 == null) {
        s1 = s;
      }

      if (!s1.equals(s)) {
        return false;
      }
    }

    return true;
  }

  public static <T extends Object> T[] arrayPush(T[] a, T e) {
    a = Arrays.copyOf(a, a.length + 1);
    a[a.length - 1] = e;
    return a;
  }

  public static String stringify(String[] array) {
    String response = "";

    if (!isEmpty(array)) {
      for (String s : array) {
        response = response + String.format("\n\r%s", s);
      }
    }

    return response;
  }

  public static String removeBackspacedChar(String str){
    str = str.replaceAll("^\b+|(?:([^\b])(?=\\1*+(\\2?+\b)))+\\2", "");
    return str;
  }

  public static void main(String[] args) {
    String[] names = new String[] { "Raja Khan" };
    names = arrayPush(names, "S Yasmin");

    for (String name : names) {
      System.out.printf("%s\n", name);
    }
  }
}
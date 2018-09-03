package org.bigdatacenter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVMain {

    public static void main(String args[]) throws IOException {
        String A = "A,B,C,D";
        String B = "A,D,S";
        String C = "D,A";
        String D = "D,B,A";
        List<List<String>> str = new ArrayList<>();
        for(String strA: A.split(",")){
          if(B.equals("")){
              List<String> s = new ArrayList<>();
              s.add(strA);
              str.add(s);
          }else{
              for(String strB: B.split(",")){
                  if(C.equals("")) {
                      List<String> s = new ArrayList<>();
                      s.add(strA);
                      s.add(strB);
                      str.add(s);
                  }else {
                      for(String strC: C.split(",")){
                          if(D.equals("")){
                              List<String> s = new ArrayList<>();
                              s.add(strA);
                              s.add(strB);
                              s.add(strC);
                              str.add(s);
                          }else{
                              for(String strD: D.split(",")){
                                  List<String> s = new ArrayList<>();
                                  s.add(strA);
                                  s.add(strB);
                                  s.add(strC);
                                  s.add(strD);
                                  str.add(s);
                              }
                          }
                      }
                  }
              }
          }
        }

        for(List<String> st: str){
          System.out.println("===> ");
          for(String s: st){
              System.out.print(s);
          }
          System.out.println();
        }
    }
}
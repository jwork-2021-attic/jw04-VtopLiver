package com.anish.calabashbros;
import java.io.*;
public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[][] a;

    @Override
    public void load(T[][] a) {
        this.a = a;
    }
    private int[] next(int i,int j,int len,int rlen){
        
        if(j<rlen-1){
            int[] tp={i,j+1};
            return tp;
        }
            
        else if(i<len-1){
            int[] tp={i+1,0};
            return tp;
        }
            
        else
            return null;
    }
    private void swap(int i1, int j1,int i2,int j2) {
        T temp;
        temp = a[i1][j1];
        a[i1][j1] = a[i2][j2];
        a[i2][j2] = temp;
        plan += "" + a[i1][j1] + "<->" + a[i2][j2] + "\n";
    }

    private String plan = "";
    
    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length; i++) {
                for(int j=0;j<a[0].length;j++){
                    int[] tmp=next(i, j,a[0].length,a.length);
                    //System.out.println(next(0,0,a[0].length,a[1].length)==a[0][1]);
                    if(tmp!=null){
                        if (a[i][j].compareTo(a[tmp[0]][tmp[1]]) > 0) {
                            swap(i,j,tmp[0],tmp[1]);
                            //System.out.println(a[0][0].compareTo(a[0][1]));
                            sorted = false;
                        }
                    }
                }
                
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}
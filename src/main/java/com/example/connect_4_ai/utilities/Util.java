package com.example.connect_4_ai.utilities;

public class Util {

    public static long setBit(long number, int index) {
        number |= 1L << index;
        return number;
    }

    public static long clearBit(long number, int index){
        number &= ~(1L << index);
        return number;
    }

    public static int getBit(long number, int index){
       return (int) ((number >> index) & 1);
    }

    public static boolean isValid(long number, int from, int to){
      for(int i = from; i <= to; i++){
          if(getBit(number,i) == 1)
             return false;
      }
        return true;
    }

    public static int getValue(long number, int from, int to){
        int counter = 0, value = 0;
        for(int i = from; i <= to; i++){
            if(getBit(number,i) == 1)
                value += Math.pow(2,counter);
            counter++;
        }
        return value;
    }

    public static long subtractOne(long number, int from, int to){
        int rightMostOne = from;
        while (from <= to && getBit(number,rightMostOne) != 1)
            rightMostOne++;
        number = clearBit(number,rightMostOne);
        rightMostOne--;
        while(rightMostOne >= from){
            number = setBit(number, rightMostOne);
            rightMostOne--;
        }
        return number;
    }
}

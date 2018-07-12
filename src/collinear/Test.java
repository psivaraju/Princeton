package collinear;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
       int[] array = new int[]{0,1,2,3,4,5};
       
       System.out.println("from 0 to 6");
       print(Arrays.copyOfRange(array, 0, 6));
       
       System.out.println("from 1 to 5");
       print(Arrays.copyOfRange(array, 1, 5));
       

    }
    
    private static void print(int[] array) {
        for(int i : array) {
            System.out.print(i + " ");
        }
    }

}

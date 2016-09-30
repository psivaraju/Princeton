package week4;

public class Test {
    public static void main(String[] args) {
        Object[] obj = new MyInterface[10];
        System.out.println(obj[0] = new MyInterface(){

            @Override
            public String toString() {
                // TODO Auto-generated method stub
                return "dudez";
            }
            
        });
    }

}

interface MyInterface {
    public String toString();
    
}

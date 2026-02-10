import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        boolean res1 = check_bit(0b1010101, 2);
        boolean res2 = check_bit(0b1010101, 5);

        System.out.println(res1);
        System.out.println(res2);

    }

    public static boolean check_bit(int num, int i){

        String byteCode = Integer.toBinaryString(num);

        return byteCode.charAt(i) == '1';
    }





}
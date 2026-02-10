public class Main {
    public static void main(String[] args) {


        long[] desiredResult = {0b0010, 0b1110, 0b1001, 0b1011};



        //boolean result = generate_neighbours(0b1010, 4) == desiredResult;

        //System.out.println(result);

        long[] res = generate_neighbours(0b1010, 4);


//        for (int i = 0; i < res.length; i++) {
//            System.out.println("desired: " + desiredResult[i] + "; got: " +  res[i]);
//        }

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }


    // n - neighbours for a given solution
    public static long[] generate_neighbours(long solution, int n){
        long[] result = new long[n];


        for( int i = 0; i < n; i++){

            result[i] = solution ^ (1 << i);
        }

        return result;
    }

    public static long toggleBit(long solution, int i){
        String solutionStr = Long.toBinaryString(solution);

        char[] bits = solutionStr.toCharArray();

        if(i != solutionStr.length()-1){
            bits[i] = (bits[i] == '1') ? '0' : '1';
        }


        long result = Long.parseLong(new String(bits), 2);

        return result;
    }
}









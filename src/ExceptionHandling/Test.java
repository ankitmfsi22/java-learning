package ExceptionHandling;

public class Test {
    public static void main(String[] args) throws Exception {
        int denominator = 0;
        try {
            if(denominator == 0){
                throw new CustomException("Divide by zero");
            }
            int b = 10 / denominator;
        }catch (Exception e){
            System.out.println("Not a custom exception");
        }

    }
}

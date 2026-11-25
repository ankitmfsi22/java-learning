package ExceptionHandling;

public class CustomException extends Exception{
    CustomException(String s){
        super(s);
    }

    CustomException(Exception a){
        super(a);
    }
}

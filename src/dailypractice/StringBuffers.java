package dailypractice;

public class StringBuffers {
    String s1 = "test";
    int Numbers;
    final static String name = "Test";
    final static String name2 = "TestUser";

    StringBuffers(int value){
        Numbers = value;
    }
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer("Hello");
        StringBuffer s2 = s1.append(" World!!11");
        System.out.println(s2.reverse());
        System.out.println(s2.capacity());
        StringBuffers stringIns = new StringBuffers(20);
        System.out.println(stringIns.Numbers);
    }
}

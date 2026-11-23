package dailypractice;

public class Arrays {
    public static void main(String[] args){
        Integer[] source = {1,2,3,4};
        Integer [] destination = new Integer[4];

        System.arraycopy(source, 0, destination, 1, destination.length - 3);

        for (Integer integer : destination) {
//            System.out.println(integer);
//            System.out.println("Copy Array ==> " + destination[integer]);
        }

    }
}

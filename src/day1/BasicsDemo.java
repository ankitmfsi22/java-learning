package day1;

public class BasicsDemo {
      public static void main(String[] args) {
          int a = 5;
          int b = 10;
          int sum = a + b;
          boolean isBig = sum > 12;
          System.out.println("sum = " + sum);
          System.out.println("isBig + " + isBig);

          // if - else if - else
          int range = 30;
            if(range>=75) {
              System.out.println("Grade A");
            }else if(range>=30){
                System.out.println("Grade B");
            }else{
                System.out.println("Grade C");
            }

          // switch
          int day = 3;
          switch (day) {
              case 1:
                  System.out.println("Monday");
                  break;
              case 2:
                  System.out.println("Tuesday");
                  break;
              case 3:
                  System.out.println("Wednesday");
                  break;
              default:
                  System.out.println("Other day");
          }

          // for loop
          System.out.println("for loop:");
          for (int i = 1; i <= 5; i++) {
              System.out.println(i);
          }

          // while loop
          System.out.println("while loop:");
          int n = 3;
          while (n > 0) {
              System.out.println(n);
              n--;
          }

          // do-while
          System.out.println("do-while loop:");
          int x = 10;
          do {
              System.out.println(x);
              x++;
          } while (x < 10);

          // break
          System.out.println("Break/continue");
          for(int i=1; i<=5; i++){
              if(i==3){
                  continue;
              }
              if(i==5){
                  break;
              }
              System.out.println(i);
          }
      }
  }



package org.firstinspires.ftc.teamcode.Basics;

public class Conditionals {

    public void main(){


       int a = 1;
       if (a > 3){
           System.out.println("yay");
           /*
            First this conditional will check if the number stored in variable 'a' is greater than 3. Is this true?
                NO! So it won't execute the print statement inside of it on ln14.
                However what if variable 'a' is set to 10. Is 10 greater than 3?
                YES! So it will print "yay"
         */
       }
       else if (a > 1){
           System.out.println("yay2");

           /*
            This statement will execute ONLY if the first condition, (a > 3) isn't true
            AND if variable 'a' is greater than 1. Is 1 greater than 1?
            NO! So it will proceed to the code below!
            */
       }
       else {
           System.out.println("awww");

           /*
            This code will print if ALL ELSE FAILS
            So if 'a' is not greater than 3
            AND if 'a' is not greater than 1
            THEN we will print out "awww"
            */
       }



    }


}

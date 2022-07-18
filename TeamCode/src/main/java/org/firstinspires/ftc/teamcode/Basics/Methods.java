package org.firstinspires.ftc.teamcode.Basics;

public class Methods {

    /*

        HELLO AND WELCOME TO METHODS

        Again I must mention, programmers are LAZY and we do not like to repeat ourselves.
        That's where methods come in! A method is a block of code that can execute just by calling
        it's name. Let's take a look at an example:

        Here we have the addFive() method. When you create a new method you can specify the following:
        - a return type (what kind of variable will be thrown back to the user)
            - void (nothing), int (integer), double (decimal number), long (big number), String (words), etc...
        - a name
        - a parameter (optional)
     */
    void doNothing(){
        // do nothing in here, ahhhhhh
    }

    /*

        Remember with Project Euler 3 where we had our isPrime() method? Well we did that because we
        didn't want to keep repeating ourselves with the same old task of finding if a number is prime.
        On a strategy level. We dissected the BIG problem into separate tasks hopefully making our jobs
        as programmers easier.

        Say we want to create a super basic method: We want to automate the task of adding five to
        any number we want. We want to call this method like so:
        */

        int sum = addFive(3);
        // Sum would be set to 8 because
        // 8 = 5 + parameter a.k.a.
        // 8 = 5 + 3

    /*
        Here we specified that the addFive() method should return and store an integer in our
        variable 'sum.' We also specified a 'parameter' - an item that the method will
        use in its own code. In this case, we want sum to be set to 5 plus our parameter, 3.
        For this we'll have to use a 'return' statement, which means we should STOP all execution
        of the method, and send back a variable.


        So in our addFive() method BELOW, we specify we will return an integer, we name the method addFive,
        and then we specify a parameter called 'number' which will store an integer. Then in the body
        of our method (in-between the curly brackets), we tell it to send us back our 'number' + 5.

     */

    int addFive(int number){
        return number + 5;
    }

    /*
        So let's call the addFive() method a couple of times.
     */
    int sum1 = addFive(4);  // What will sum1 be set to?
    int sum2 = addFive(19); // What will sum2 be set to?

    /*
        We'll go through a DEBUGGING session in class as to how it works
     */

}

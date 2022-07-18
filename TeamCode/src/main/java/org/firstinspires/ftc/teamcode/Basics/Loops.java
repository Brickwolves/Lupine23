package org.firstinspires.ftc.teamcode.Basics;

public class Loops {

    public static void main(){

        /*

            This is a while loop. Loops are pretty handy when you need to complete repetitive tasks.
            After all, programmers are LAZY and want to do as little work as possible.
            The while loop's syntax looks like this:

                while (conditional_statement){
                    something_to_execute
                }

            Basically, while the conditional_statement is true, we'll repeatedly execute whatever
            code is inside the while loop's curly brackets.

            Say for example we wanted to print out all numbers 0 -> 9. Well how do humans do that normally?
            We start at 0, then we add 1 to our previous number repeatedly UNTIL we reach 9.

            Well we can replicate that with code! We can 'remember' our starting number by
            initializing a 'counter' variable with 0. Then we want the while loop to repeatedly
            execute something UNTIL we reach 9. What are we doing inside the while loop's curly
            brackets? Well we're just adding 1 to the counter variable.
         */

        int counter = 0;
        while (counter < 10){
            counter = counter + 1;
        }

        /*
            Let's look at how this code will run
            set counter to 0
            1)
                counter is currently 0
                is counter less than 10? YES
                    so let's add 1 to counter
            2)
                counter is currently 1
                is counter less than 10? YES
                    so let's add 1 more to counter
            3)
                counter is currently 2
                is counter less than 10? YES
                    so let's add 1 more to counter

            and so it goes on....

            10) counter is currently 10
                is counter less than 10? NO!
                so we quit repeating!
         */



        /*
            The for loop is EXACTLY like the previous example, but just a bit more condensed.
            You see there are three sections to the condition of a for loop:

            (counter_variable  ;  conditional_statement  ;  how the counter_variable should change each loop)

            Instead of initializing our counter variable outside the loop, we initialize it IN the loop.
            How cool is that! This code does the exact same as the previous while loop.
         */
        for (int new_counter=0; new_counter < 10; new_counter = new_counter + 1){
            // Something to execute repeatedly
        }

    }
}

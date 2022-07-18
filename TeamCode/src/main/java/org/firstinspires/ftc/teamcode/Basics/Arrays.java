package org.firstinspires.ftc.teamcode.Basics;

import java.util.ArrayList;

public class Arrays {

    public static void main(){

        /*
            WELCOME TO ARRAYS

            Arrays are basically lists of items or variables.
            Think of a grocery list where you keep a list of words on a piece of paper:
                - apples
                - bananas
                - bread
                - uranium isotopes

            The array object is basically the piece of paper that holds the list of words
            In fact, let's make a grocery list! Our very first array.
         */
        String[] groceryList = {
                "apples",
                "bananas",
                "bread",
                "uranium isotopes",
        };
        /*
            Fantastic! You'll notice that just like other variable declarations, we need to declare
            what type of items the list will be holding. In our case we wanted Strings, so we typed
            String[] <name of grocery list> = {
                "item1",
                "item2",
                "item3"
            }

            Now what if you're out shopping with your family member, Uncle Johnny, and they ask you,
                "Hey, what's the 4th item on the list?"
            Naturally you'd look to the 4th item, "uranium isotopes".
            HOWEVER, arrays and computers a bit tricky. Computers don't start at 1 and count up.
            Rather, they start at 0, and count up. This is how computer's view your grocery list

                - apples        0th item
                - bananas       1st item
                - bread         2nd item
                - uranium       3rd item

            Say for example your Uncle Johnny said to get the 4th item, what item is that for
            the computer?
                It would be the 3rd item!
            We can 'index' into, or 'grab' that specific item from an array using the following syntax:
         */
        String fourthItem = groceryList[3];
        /*
            Now the fourthItem will contain 'uranium'
            But what if your Uncle Johnny said,
            "Uh oh, I didn't mean to put that item there. Can you change that to oranges?"
            As the kind samaritan you are, you say "Of course!"

            You can change a specific item in an array like so:
         */

        groceryList[3] = "oranges";

        /*
            Awesome! You've successfully changed the 4th item of grocery list (the 3rd item
            relative to the computer). Now your Uncle Johnny says, let's add an item to the list
            UH OH.
            The issue with basic arrays however is that you can't ADD, or DELETE items!
            They are of a fixed SIZE. This is due to how the array datatype works in the computer's
            memory.
            LUCKILY, we have a datatype that's like an array, but on steroids!
         */



        /*

            ARRAY LISTS

            An array list is a malleable array, meaning it can be changed easily. The procedures are
            a bit different however. Say you wanted to make a new grocery list as an array list, easy peasy.
            Just like last time we need to specify the type of items we'll be storing, and use some
            new syntax. This is how:
         */
        ArrayList<String> groceryList2 = new ArrayList<>();
        /*
            AWESOME. However it's currently empty... let's add some items
                - apples
                - bananas
                - bread
                - dynamite
            Well you can do so with the 'add' method.
         */
        groceryList2.add("apples");
        groceryList2.add("bananas");
        groceryList2.add("bread");
        groceryList2.add("dynamite");
        /*
            Nice, we've done that. Now your Uncle Johnny is forgetful of course, so he asks what
            the 1st item is. You know computers are a bit different than humans, and they think the
            1st item is in the 0th item of groceryList2. Let's grab that value with the 'get' method.
         */
        String firstItem = groceryList2.get(0);
        /*
            Woohoo. We did it. Now Uncle Johnny wishes to remove the 4th item from the list...
            Fortunately there's two ways of doing so:
         */
        groceryList2.remove(3);         // THIS
        groceryList2.remove("dynamite");   // OR YOU CAN DO THIS!

        /*
            How cool is that? Aren't ArrayLists awesome? They are certainly helpful. Thanks for
            helping Uncle Johnny!
         */

    }

}

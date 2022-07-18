package org.firstinspires.ftc.teamcode.Basics;

public class Classes {

    /*

        HELLO AND WELCOME TO OUR CLASS ABOUT CLASSES!

        You're probably wondering, so what is a class? A class is the blueprint from which individual objects are created.
        Now you're wondering, OK, what's an object? An object is when you take that blueprint, and make it into the real thing.
        It's an 'instance' or 'object' that was created from that blueprint. Let's look at an example:

        We're a company selling TI84 calculators. You're their lead programmer writing all the software.
        Well what does our calculator need to be useful to our customers?
        It probably needs...
            - addition
            - subtraction
            - multiplication
            - division
            - memory storage
            - a color if we're being fancy
            - a version
            - name tag for the owner
        And there! We just created a sort of blueprint of our calculator. If someone were to use it
        in Java, we'd want to be able to create an instance of our Calculator blueprint. It would
        look something like the below. Let's create a new bright blue calculator and name it TI84:

        Usage: Calculator <name_of_variable> = new Calculator();
        Example:
    */
        Calculator my_calculator = new Calculator();
    /*
        That looks a lot similar to the ArrayList instantiation but without the < > !
        Reference: ArrayList<String> = new ArrayList<>();

        We just created a variable, but of type 'Calculator.' You could also say you created an
        instance of Calculator, or even that you created a Calculator object. These are all
        interchangeable at the moment. We would then be able to use our my_calculator variable to call certain
        methods of the Calculator class like so:

        my_calculator.add(2, 1);         // Should give us 2
        my_calculator.subtract(3, 2);    // Should give us 1
        my_calculator.multiply(5, 6);    // Should give us 30
        my_calculator.divide(6, 3);      // Should give us 2

        Ok so... how do we do this? Well let's start by making the base of a class:
     */

    public class Calculator {

        public Calculator(){
            /*
                I am in the CONSTRUCTOR
             */
        }
    }

    /*

        Awesome, you've created your first class called Calculator. By convention, it's important
        that your class name be properly capitalized. Well you see up there something that looks
        an awful lot like a method. The public Calculator(){ } is called the CONSTRUCTOR. Remember
        when you created your first instance of Calculator called TI84? You basically made a call to
        the Calculator constructor as if it were a method.

        In our case let's initialize some attributes - class variables that will be created with every
        instance of our Calculator class. What's cool about attributes, is that the every method of
        the Calculator1 class can access them!!!!

        I will create a new Calculator class since I can no longer add to the old one.
     */

    public class Calculator1 {

        public int version;
        public String owner;
        public String color;

        public Calculator1(int input_version, String input_owner, String input_color){
            version = input_version;
            owner   = input_owner;
            color   = input_owner;
        }
    }
    /*

        Great, we now have a new class called Calculator1, which holds three 'attributes' or variables.
        These are version, owner, and color, and each respectively set to input_version, input_owner,
        and input_color. So how do we call this new calculator? Just like last time, but with some
        parameters:
    */
        Calculator1 my_calculator1 = new Calculator1(1, "BillyBob Jr", "blue");
    /*
        We just created a new instance of our new and improved Calculator1 class. Now you'll notice
        we said:
            public int version;
            public String owner;
            public String color;
        You're probably wondering what 'public' means. It means that these variables are accessible from
        all Calculator1 instances. For example I could find out who owns the my_calculator1 object
        by printing out:
            my_calculator1.owner
        Or I could find out the version of this calculator by printing out:
            my_calculator1.version

        But UH OH, if the attributes are publicly available then we can also change those values.
        Someone who's being malicious could easily take our calculator, and write their name on it:
            my_calculator.owner = "Malicious Meany Person"

        But if I were to make the attributes private, then I wouldn't be able to see them at all!
        Fortunately for us, we can add some methods to ONLY retrieve attributes, and not SET them.

        Let's create a new Calculator class where instead, our attributes are private, and we can
        access them from the outside via methods;
     */

    public class Calculator2 {

        private int version;
        private String owner;
        private String color;

        public Calculator2(int input_version, String input_owner, String input_color){
            version = input_version;
            owner   = input_owner;
            color   = input_owner;
        }

        public int getVersion(){
            return version;
        }
        public String getOwner(){
            return owner;
        }
        public String getColor(){
            return color;
        }
    }
    /*
        Let's make an instance of that real quick:
     */
    Calculator2 my_calculator2 = new Calculator2(2, "BillyBob Jr", "blue");
    /*
        Wow. So since our attributes are 'private' now, we can't access them via:
            my_calculator2.version
            my_calculator2.owner
            my_calculator2.color

        Rather, we can now retrieve their values via the 'public' methods - methods that are accessible
        externally through the following syntax:
            my_calculator2.getVersion();        // will return 2, which is the version
            my_calculator2.getOwner();          // will return "BillyBob Jr", the owner
            my_calculator2.getColor();          // will return "blue", the color

        That's awesome, we now have one issue. Our calculator hasn't being doing much calculating.
        We set goals for our calculator earlier didn't we? We wanted to make it be able to perform
        addition, subtraction, multiplication, and division in the following syntax:

            my_calculator.add(2, 1);         // Should give us 2
            my_calculator.subtract(3, 2);    // Should give us 1
            my_calculator.multiply(5, 6);    // Should give us 30
            my_calculator.divide(6, 3);      // Should give us 2

        We'll let's add some more methods, and make sure they're publicly accessible!
     */
    public class Calculator3 {

        // Attributes
        private int version;
        private String owner;
        private String color;

        // Constructor              // Constructor Parameters
        public Calculator3(int input_version, String input_owner, String input_color){

            // input_version, input_owner, input_color only exist within this constructor now
            // We can't access these variables anywhere else.
            // In order to store these, we need to set the attributes to the respective pieces
            version = input_version;
            owner   = input_owner;
            color   = input_color;
        }

        public int getVersion(){
            return version;
        }
        public String getOwner(){
            return owner;
        }
        public String getColor(){
            return color;
        }
        public int add(int a, int b){
            int sum = a + b;
            return sum;
        }
        public int subtract(int a, int b){
            int difference = a - b;
            return difference;
        }
        public int multiply(int a, int b){
            int product = a * b;
            return product;
        }
        public int divide(int a, int b){
            int quotient = a / b;
            return quotient;
        }
    }

    // And let's create an instance of that
    Calculator3 my_calculator3 = new Calculator3(3, "BillyBob Jr", "blue");

    /*
        Phew. That's a whole lotta code now. Okay so we now have a fully fledged calculator class!
        That's incredible. We can make an instance of it with the above syntax, and we learned all
        about constructors, public and private modifiers, attributes, class methods. You'll notice
        we put everything inside of one file here: Classes.java. However, it's most common to separate
        your classes into different files.

        Fun Fact:
        You'll notice in IntelliJ, we always use a Main.java file.
        Well Main.java is in itself a class! It's a very special class that doesn't require a
        constructor. However it does have a singular main() method.

        Next:
        We'll be learning about Inheritance and Polymorphism.
     */
}

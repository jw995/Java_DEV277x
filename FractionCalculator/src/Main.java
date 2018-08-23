import java.util.Scanner;

public class Main {
    public static void main(String args[]){

        //create instances
        Scanner input = new Scanner(System.in);

        // intro
        System.out.print("\n" + "Welcome! This program is a fraction calculator.\n" +
                "It will add, subtract, multiply, divide and compare fractions " +
                "until you type 'Q' to quit.\n" + "Please enter a fraction in the " +
                "form of 'a/b' or 'a' where a and b are integers.\n" +
                "----------------------------------------------------------\n\n");

        // enter an operation
        String operator = getOperation(input);
        while (!operator.equalsIgnoreCase("Q")){

            Fraction fraction1 = getFraction(input);
            Fraction fraction2 = getFraction(input);

            if (operator.equals("+")){
                Fraction outcome = fraction1.add(fraction2);
                System.out.println(fraction1.toString()+" + "+fraction2.toString()+" = "+outcome.toString());
            }
            else if (operator.equals("-")){
                Fraction outcome = fraction1.sub(fraction2);
                System.out.println(fraction1.toString()+" - "+fraction2.toString()+" = "+outcome.toString());
            }
            else if (operator.equals("*")){
                Fraction outcome = fraction1.mul(fraction2);
                System.out.println(fraction1.toString()+" * "+fraction2.toString()+" = "+outcome.toString());
            }
            else if (operator.equals("/")){
                Fraction outcome = fraction1.div(fraction2);
                System.out.println(fraction1.toString()+" / "+fraction2.toString()+" = "+outcome.toString());
            }
            else {
                System.out.println(fraction1.toString() + " = " + fraction2.toString() + " is " +
                        fraction1.equals(fraction2));
            }

            operator = getOperation(input);

        }

    }






    public static String getOperation(Scanner input){
        //verify if it is a valid operator

        System.out.print("Please enter an operation (+, -, *, /, =, or Q to quit): ");
        String operation = input.nextLine();
        String validInput = "+-*/=qQ";

        while (!(validInput.contains(operation) && operation.length() == 1)){
            System.out.print("Please enter an operation (+, -, *, /, =, or Q to quit): ");
            operation = input.nextLine();
        }
        return operation;
    }

    public static boolean ValidInput(String input){
        if (input.contains("-")){
            if ((input.indexOf("-")) != 0){
                return false;
            }else {
                // String.substring(int beginIndex), remove the "-"
                input = input.substring(1);
            }
        }

        if (input.contains("/")){
            int opt = input.indexOf("/");
            String numm = input.substring(0,opt);
            String denn = input.substring(opt+1);

            // "[0-9]+": contains only 0-9 one or more than onetime
            if (!numm.isEmpty() && !denn.isEmpty() &&
                    isNumber(numm) && isNumber(denn) &&
                    Integer.parseInt(denn) != 0){
                return true;
            }else{
                return false;
            }
        }

        else{
            if (input.matches("[0-9]+")){
                return true;
            }
        }
        return false;
    }


    public static Fraction getFraction(Scanner input){

        // get a valid user input and create Fraction obj
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String UserInput = input.nextLine();

        while(!ValidInput(UserInput)){
            // if the input is not valid, let the user input again
            System.out.print("Please enter a fraction (a/b) or integer (a): ");
            UserInput = input.nextLine();
        }

        if (UserInput.contains("/")){
            String[] split = UserInput.split("/");
            int num = Integer.parseInt(split[0]);
            int den = Integer.parseInt(split[1]);
            return new Fraction(num,den);
        }else{
            return new Fraction(Integer.parseInt(UserInput));
        }
    }


    public static boolean isNumber(String input){
        if (input.matches("[0-9]+")){
            return true;
        }else return false;
    }

}
